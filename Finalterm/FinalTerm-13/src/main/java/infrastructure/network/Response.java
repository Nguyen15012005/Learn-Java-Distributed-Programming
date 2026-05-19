package infrastructure.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response implements Serializable {
    private boolean success;
    private String message;
    private Object data;
}
