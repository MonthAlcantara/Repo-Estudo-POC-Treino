package io.github.monthalcantara.bibliotecaCDD.advice;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodNotValidException(MethodArgumentNotValidException e) {
        List<String> listErrors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity(new ErrosApi(listErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity(transformError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity tamanhoMaximoArquivoException(MultipartException e) {
        return new ResponseEntity(transformError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity(transformError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity nullPointerExceptionException(NullPointerException e) {
        return new ResponseEntity(transformError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity illegalStateException(IllegalStateException e) {
        return new ResponseEntity(transformError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private ErrosApi transformError(String message) {
        return new ErrosApi(message);
    }

    class ErrosApi {

        @Getter
        List<String> erros;

        public ErrosApi(String e){
            this.erros = Arrays.asList(e);
        }

        public ErrosApi(List<String> listErrors) {
            this.erros = listErrors;
        }
    }
}
