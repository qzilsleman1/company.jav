import java.util.Date;

public class Coupon {
    private int id;
    private int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    public Coupon(int id, String title, Date startDate, Date endDate, int amount, Category category, String description, double price, String image) {
        this.id = this.id;
        this.companyID = companyID;
        this.category = this.category;
        this.title = this.title;
        this.description = description;
        this.startDate = this.startDate;
        this.endDate = this.endDate;
        this.amount = this.amount;
        this.price = this.price;
        this.image = this.image;
    }

    public Coupon(int id, String title, Date startDate, Date endDate, int amount, Category category, String description, double price) {
        this.id = this.id;
        this.companyID = companyID;
        this.category = this.category;
        this.title = this.title;
        this.description = description;
        this.startDate = this.startDate;
        this.endDate = this.endDate;
        this.amount = this.amount;
        this.price = this.price;
    }

 /*   public Coupon(int id, String c1, int i, int i1, int amount, Category food, String nothing, double price, String image) {

    }*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    public String getMessage() {
        return null;
    }
}

