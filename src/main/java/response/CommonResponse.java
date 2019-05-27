package response;

public class CommonResponse<T> {
    private int responsecode;
    private String responsemessage;
    private T data;

    public CommonResponse(){
        this.responsecode= 01;
        this.responsemessage= "success";
    }
    public CommonResponse(int responsecode, String responsemessage){
        this.responsecode=responsecode;
        this.responsemessage=responsemessage;
    }

    public int getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(int responsecode) {
        this.responsecode = responsecode;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
