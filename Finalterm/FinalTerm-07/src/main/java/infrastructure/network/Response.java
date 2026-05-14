package infrastructure.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private boolean success;
    private String message;
    private Object data;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
