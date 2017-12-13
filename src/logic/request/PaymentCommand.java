package logic.request;

public class PaymentCommand implements ICommand {
    @Override
    public String execute(String context) {

        context = context.replace(context.split("%20")[0] + "%20", "");
        switch (context.split("%20")[0]) {
            case "VELCOM":
                context = context.replace("VELCOM", "9283 8291 2039 2934");
                break;
            case "LIFE":
                context = context.replace("LIFE", "3345 3323 4444 4444");
                break;
            case "MTC":
                context = context.replace("MTC", "6969 6969 6969 6969");
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        return new TransferCommand().execute(context);
    }
}
