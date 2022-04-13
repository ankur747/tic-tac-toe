package exceptions;

import lombok.Getter;

@Getter
public class InvalidMoveException extends Exception{
    private String message;

    public InvalidMoveException()
    {
        message = "Invalid Move";
    }
}
