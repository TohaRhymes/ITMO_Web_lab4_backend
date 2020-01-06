package app.response;


import lombok.Data;

@Data
public class BaseResponse {
    private int status;
    private Object answer;

    public BaseResponse(int status, Object answer) {
        this.status = status;
        this.answer = answer;
    }

//
//    //todo нужно ли именно стринг???
//    public void setAnswer(String answer) {
//        this.answer = answer;
//    }
}
