package smartcity.kni.wirtualnaapteczka.validators;

/**
 * Created by Aleksander on 12.06.2018.
 */

public class ValidationResponse {

    boolean success;
    String msg;

    public ValidationResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }
}
