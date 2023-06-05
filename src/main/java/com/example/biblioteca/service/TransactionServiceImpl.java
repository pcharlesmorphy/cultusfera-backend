package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.pojo.Mail;
import com.example.biblioteca.pojo.StatusCopyType;
import com.example.biblioteca.pojo.TransactionStatusType;
import com.example.biblioteca.repository.IBookingRepository;
import com.example.biblioteca.repository.ILoanRepository;
import com.example.biblioteca.repository.ITransactionRepository;
import com.example.biblioteca.repository.ITransactionStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements ITransactionService{

    @Autowired
    ITransactionRepository transactionRepo;
    @Autowired
    CopyServiceImpl copyService;

    @Autowired
    ILoanRepository loanRepo;

    @Autowired
    IBookingRepository bookingRepo;

    @Autowired
    ITransactionStatusRepository transactionStatusRepo;

    @Autowired
    EmailServiceImpl emailService;

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepo.findAll();
    }

    @Override
    public Optional<Loan> saveLoan(Loan loan) {
        if (checkLoan(loan)){
            completeLoanData(loan);
            return Optional.of(loanRepo.save(loan));
        }
        return Optional.empty();
    }

    private boolean checkLoan (Loan loan){
        Copy loanCopy = loan.getCopy();
        List<Transaction> transactions = loanCopy.getTransactions();
        Optional<Booking> booking = getCopyBooking(transactions);

        Long loanCopyId = loan.getCopy().getId();
        Long loanUserId = loan.getUser().getId();
        if (!booking.isEmpty()){
            Long bookingUserId = booking.get().getUser().getId();
            //El estado es Reservado y ademas el usuario que hace el prestamo es igual al de la reserva
            //Eliminamos la reserva para seguir con el prestamo.

            if (loanCopy.getStatus().getStatus().equals(StatusCopyType.RESERVADO.getType()) && loanUserId.equals(bookingUserId)){

                bookingRepo.deleteById(booking.get().getId());

                return true;
            }
        }

        //El estado de la copia es Disponible y el usuario tiene 4 o menos préstamos en curso puede hacer el préstamo

        if ((loanCopy.getStatus().getStatus().equals(StatusCopyType.DISPONIBLE.getType()) && (getActiveLoansByUser(loan.getUser()).size() <=4)))
        {
            return true;
        }

        //En el resto de los casos no se puede hacer el préstamo

        return false;
    }

    private Optional<Booking> getCopyBooking (List<Transaction> transactions){

        for (Transaction t:transactions){
            if (t instanceof Booking) {
                return Optional.of((Booking) t);
            }
        }
        return Optional.empty();
    }


    private void completeLoanData(Loan loan){
        TransactionStatus transactionStatus = new TransactionStatus();
        transactionStatus.setId(TransactionStatusType.PRESTAMO_EN_CURSO.getId());
        transactionStatus.setType(TransactionStatusType.PRESTAMO_EN_CURSO.getType());
        StatusCopy statusCopy = new StatusCopy();
        statusCopy.setId(StatusCopyType.PRESTADO.getId());
        statusCopy.setStatus(StatusCopyType.PRESTADO.getType());
        loan.getCopy().setStatus(statusCopy);
        loan.setStatus(transactionStatus);
    }
    @Override
    public Optional<Booking> saveBooking(Booking booking) {
        if (checkBooking(booking)){
            completeBookingData(booking);
            return Optional.of(bookingRepo.save(booking));
        }
        return Optional.empty();
    }

    private void completeBookingData(Booking booking){
        TransactionStatus transactionStatus= new TransactionStatus();
        transactionStatus.setId(TransactionStatusType.RESERVA_EN_CURSO.getId());
        transactionStatus.setType(TransactionStatusType.RESERVA_EN_CURSO.getType());
        StatusCopy statusCopy = new StatusCopy();
        statusCopy.setId(StatusCopyType.RESERVADO.getId());
        statusCopy.setStatus(StatusCopyType.RESERVADO.getType());
        booking.getCopy().setStatus(statusCopy);
        booking.setStatus(transactionStatus);

    }
    private boolean checkBooking (Booking booking) {
        List<Transaction> transactionsByResource = findAllTransactionsByResource(booking.getCopy().getResource());
        Copy bookingCopy = booking.getCopy();
        Long bookingCopyId = booking.getCopy().getId();
        Long bookingUserId = booking.getUser().getId();

        //No se puede reservar si el estado de la copia no es Prestado
        if (!bookingCopy.getStatus().getStatus().equals(StatusCopyType.PRESTADO.getType())){
            return false;
        }


        //Si el id de la copia de la reserva es igual al id de la copia y el usuario tambien es el mismo no se hace la reserva
        //puesto que el usuario ya tiene una reserva en curso.

        for (Transaction t:transactionsByResource){
            if ( bookingCopyId.equals(t.getCopy().getId()) && bookingUserId.equals(t.getUser().getId())){
                return false;
            }

        }


        return true;
    }



    @Override
    public Boolean delete(Long id) {
        if (findById(id).isEmpty()) return false;
        transactionRepo.deleteById(id);
        return true;
    }

    @Override
    public Boolean deleteBooking(Long id) {
        Optional<Transaction> booking = findById(id);
        if (booking.isEmpty()) return false;
        Copy bookingCopy = booking.get().getCopy();
        StatusCopy status = new StatusCopy();

        if (booking.get().getEndDate() == null){
            status.setId(StatusCopyType.PRESTADO.getId());
            status.setStatus(StatusCopyType.PRESTADO.getType());
        }else {
            status.setId(StatusCopyType.DISPONIBLE.getId());
            status.setStatus(StatusCopyType.DISPONIBLE.getType());
        }
        bookingCopy.setStatus(status);
        copyService.update(bookingCopy);
        bookingRepo.deleteById(id);

        return true;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.of(transactionRepo.findById(id)).orElse(null);
    }

    @Override
    public Optional<Loan> findLoanById (Long id){
        return Optional.of(loanRepo.findById(id)).orElse(null);
    }

    @Override
    public Loan updateLoan (Loan loan){
        StatusCopy status = new StatusCopy();
        Copy copy = loan.getCopy();
        List<Transaction> transactions = copy.getTransactions();
        Optional<Booking> booking = getCopyBooking(transactions);
        if (!booking.isEmpty()){
            LocalDate today = LocalDate.now();
            LocalDate endBookingDate = today.plusDays(7);
            booking.get().setEndDate(endBookingDate);
            createBookingExpirationNotificationMail(booking.get());
        }else {
            status.setId(StatusCopyType.DISPONIBLE.getId());
            status.setStatus(StatusCopyType.DISPONIBLE.getType());
            copy.setStatus(status);
            copyService.update(copy);
        }

        return loanRepo.save(loan);
    }
    @Override
    public List<Transaction> findAllTransactionsByResource (Resource resource){
        List<Transaction> transactions = new ArrayList<Transaction>();
        List<Copy> copies = copyService.findAllResourceCopies(resource);
        for (Copy copy:copies){
            transactions.addAll(transactionRepo.findAllByCopy(copy));
        }
        return transactions;
    }

    @Override
    public List<Transaction> findAllTransactionsByUser(User user) {
        return transactionRepo.findAllByUser(user);
    }

    @Override
    public Optional<TransactionStatus> getTransactionStatusByType(String type) {
        return Optional.ofNullable(transactionStatusRepo.getByType(type)).orElse(null);
    }

    @Override
    public List<Transaction> getActiveLoansByUser(User user) {
        List<Transaction> allLoans = transactionRepo.findAllByUser(user);
        List<Transaction> currentLoans = new ArrayList<Transaction>();
        for (Transaction t:allLoans) {
            if (t.getStatus().getId() == TransactionStatusType.PRESTAMO_EN_CURSO.getId() ||
                    t.getStatus().getId() == TransactionStatusType.PRESTAMO_VENCIDO.getId()) {

                currentLoans.add(t);
            }
        }

        return currentLoans;
    }

    @Override
    public List<Transaction> getCompletedLoansByUser(User user) {
        List<Transaction> allLoans = transactionRepo.findAllByUser(user);
        List<Transaction> completedLoans = new ArrayList<Transaction>();
        for (Transaction t:allLoans){
            if (t.getStatus().getId() == TransactionStatusType.PRESTAMO_FINALIZADO.getId()){
                completedLoans.add(t);
            }
        }

        return completedLoans;
    }

    @Override
    public List<Transaction> getActiveBookingsByUser(User user) {
        List<Transaction> allBookings = transactionRepo.findAllByUser(user);
        List<Transaction> currentBookings = new ArrayList<Transaction>();
        for (Transaction t:allBookings){
            if (t.getStatus().getId() == TransactionStatusType.RESERVA_EN_CURSO.getId()) {
                currentBookings.add(t);
            }
        }

        return currentBookings;
    }


    public List<Copy> setStatusCopyList (List<Copy>copies){

        for (Copy c:copies){
            c.setTransactionStatus(findStatusCopy(c.getId()).get());
        }

        return copies;
    }


    public Optional<TransactionStatus> findStatusCopy (Long id){

        if (copyService.findCopyById(id).isEmpty()){
            return Optional.empty();
        }
        Copy copy =  copyService.findCopyById(id).get();
        List<Transaction> transactions = transactionRepo.findAllByCopy(copy);
        if (transactions.isEmpty()) {
            return Optional.empty();
        }

        Transaction transaction = transactions.get(transactions.size() -1);
        return Optional.ofNullable(transaction.getStatus());
    }

    @Override
    public List<Loan> findAllLoans() {
        return loanRepo.findAll();
    }

    @Scheduled(cron="0 0 8 * * *")
    public void checkLoansExpirationDate(){
        //Verifica los vencimientos de los préstamos cada día a las 8

        List<Loan> loans = findAllLoans();
        LocalDate todayDate = LocalDate.now();
        TransactionStatus status = new TransactionStatus();

        status.setId(TransactionStatusType.PRESTAMO_VENCIDO.getId());
        status.setType(TransactionStatusType.PRESTAMO_VENCIDO.getType());

        for (Loan l:loans){
            if (l.getEndDate().minusDays(1).isEqual(todayDate)
                    && l.getStatus().getType().equals(TransactionStatusType.PRESTAMO_EN_CURSO.getType())){
                createLoanExpirationNotificationMail(l);
            }
            if (l.getEndDate().plusDays(1).isEqual(l.getEndDate())
                    && l.getStatus().getType().equals(TransactionStatusType.PRESTAMO_EN_CURSO.getType())){
                l.setStatus(status);
                saveLoan(l);
            }
        }
    }

    public void createLoanExpirationNotificationMail (Transaction transaction) {

        String userMail = transaction.getUser().getEmail();
        String message = createLoanExpirationMailMessage(transaction);
        Mail mail = new Mail (userMail,message);
        emailService.sendUserExpirationWarningMail(mail);
    }

    public String createLoanExpirationMailMessage (Transaction transaction){
        String username = transaction.getUser().getName();
        String title = transaction.getCopy().getResource().getTitle();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola ").append(username)
                .append("\n\n\n")
                .append("Te recordamos que mañana día ").append(transaction.getEndDate()).append(" finaliza el préstamo del recurso ")
                .append(title).append(".").append("\n\n\n")
                .append("Un saludo cordial del equipo de CultuSfera");
        return stringBuilder.toString();
    }

    public void createBookingExpirationNotificationMail (Transaction transaction){

        String userMail = transaction.getUser().getEmail();
        String message = createBookingExpirationMailMessage(transaction);
        Mail mail = new Mail (userMail,message);
        emailService.sendUserExpirationWarningMail(mail);
    }

    public String createBookingExpirationMailMessage (Transaction transaction){
        String username = transaction.getUser().getName();
        String title = transaction.getCopy().getResource().getTitle();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola ").append(username)
                .append("\n\n\n")
                .append("El recurso ").append(title).append(" que reservaste el dia ").append(transaction.getStartDate()).append(" ya está disponible.\n")
                .append("Tienes hasta el próximo día ").append(transaction.getEndDate()).append(" para realizar el préstamo del recurso ")
                .append(title).append(".").append("\n\n\n")
                .append("Un saludo cordial del equipo de CultuSfera");
        return stringBuilder.toString();
    }
}
