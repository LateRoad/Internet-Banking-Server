package logic.entity;

public class Card {
    private String id;
    private String owner;
    private String number;
    private String password;
    private String secretNumber;
    private String endDate;
    private String money;

    public Card(String id, String owner, String number, String password, String secretNumber, String endDate, String money) {
        this.id = id;
        this.owner = owner;
        this.number = number;
        this.password = password;
        this.secretNumber = secretNumber;
        this.endDate = endDate;
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(String secretNumber) {
        this.secretNumber = secretNumber;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", secretNumber='" + secretNumber + '\'' +
                ", endDate='" + endDate + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
