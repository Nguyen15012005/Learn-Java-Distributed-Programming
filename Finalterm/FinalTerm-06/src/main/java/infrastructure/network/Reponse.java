package infrastructure.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reponse implements Serializable {
    private boolean success;
    private String message;
    private Object data;

    public Reponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
