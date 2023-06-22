package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.BookDTO;
import com.example.biblioteca.model.dto.BookingDTO;
import com.example.biblioteca.model.dto.LoanDTO;
import com.example.biblioteca.model.dto.UserDTO;
import com.example.biblioteca.model.dto.backend.ReturnLoanDTO;
import com.example.biblioteca.model.dto.frontend.FrontendTransactionDTO;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.CopyServiceImpl;
import com.example.biblioteca.service.TransactionServiceImpl;
import com.example.biblioteca.service.UserServiceImpl;
import com.example.biblioteca.service.mappers.IBookingMapper;
import com.example.biblioteca.service.mappers.ILoanMapper;
import com.example.biblioteca.service.mappers.TransactionStatusMapperImpl;
import com.example.biblioteca.service.mappers.frontend.FrontendTransactionMapperImpl;
import com.example.biblioteca.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/transacciones")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    ILoanMapper loanMapper;

    @Autowired
    IBookingMapper bookingMapper;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CopyServiceImpl copyService;

    @Autowired
    TransactionStatusMapperImpl transactionStatusMapper;

    @Autowired
    FrontendTransactionMapperImpl frontendTransactionMapper;



    @GetMapping("/pruebas")
    public ResponseEntity<Loan> getAllLoans(){
        List<Loan> loans = transactionService.findAllLoans();
        transactionService.checkLoansExpirationDate();

        return new ResponseEntity(loans, HttpStatus.CREATED);
    }

    @PostMapping("/prestamos")
    public ResponseEntity<String> addLoan (@RequestBody LoanDTO loanDTO){

        User user = userService.findById(loanDTO.getUserId()).get();
        Copy copy = copyService.findCopyById(loanDTO.getCopyId()).get();
        Loan loan = loanMapper.dtoToEntity(loanDTO);
        loan.setUser(user);
        loan.setCopy(copy);
        Optional<Loan> saveLoan = transactionService.saveLoan(loan);
        if (saveLoan.isEmpty()){
            String message = "El usuario no puede realizar préstamos: Está sancionado o bien ha alcanzado el máximo permitido de 5 préstamos en curso. ";
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/prestamos")
    public ResponseEntity returnLoan (@RequestBody ReturnLoanDTO returnLoan){

        Optional<Loan> loan = transactionService.findLoanById(returnLoan.getId());
        TransactionStatus status = new TransactionStatus();
        if (loan.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        loan.get().setStatus(transactionStatusMapper.dtoToEntity(returnLoan.getStatus()));

        return new ResponseEntity(transactionService.updateLoan(loan.get()),HttpStatus.CREATED);
    }

    @PostMapping("/reservas")
    public ResponseEntity <String> addBooking (@RequestBody BookingDTO bookingDTO){

        User user = userService.findById(bookingDTO.getUserId()).get();
        Copy copy = copyService.findCopyById(bookingDTO.getCopyId()).get();
        Booking booking = bookingMapper.dtoToEntity(bookingDTO);
        booking.setUser(user);
        booking.setCopy(copy);
        Optional<Booking> saveBooking = transactionService.saveBooking(booking);
        if (saveBooking.isEmpty()){
            String message = "No se puede reservar: Usuario con préstamo en curso o usuario sancionado.";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Boolean> deleteBooking (@PathVariable("id") Long id){

        if (!transactionService.deleteBooking(id)){
            return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/prestamos/{username}")
    public ResponseEntity <List<FrontendTransactionDTO>> getActiveUserLoans (@PathVariable("username") String username){
        if (userService.findUserByUsername(username).isEmpty()){
            return ResponseEntity.notFound().build();        }

        User user = userService.findUserByUsername(username).get();

        List<Transaction> currentLoans = transactionService.getActiveLoansByUser(user);

        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(currentLoans));
    }


    @GetMapping("/reservas/{username}")
    public ResponseEntity <List<FrontendTransactionDTO>> getActiveUserBookings (@PathVariable("username") String username){
        if (userService.findUserByUsername(username).isEmpty()){
            return ResponseEntity.notFound().build();        }

        User user = userService.findUserByUsername(username).get();

        List<Transaction> currentBookings = transactionService.getActiveBookingsByUser(user);

        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(currentBookings));
    }

    @GetMapping("/")
    public ResponseEntity <List<FrontendTransactionDTO>> getActiveUserTransactions (@RequestParam ("name") String name,@RequestParam("surnames") String surnames){
        if (userService.findByName(name,surnames).isEmpty()){
            return ResponseEntity.notFound().build();        }

        User user = userService.findByName(name,surnames).get();

        List<Transaction> transactions = transactionService.getActiveLoansByUser(user);
        transactions.addAll(transactionService.getActiveBookingsByUser(user));


        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(transactions));
    }

}
