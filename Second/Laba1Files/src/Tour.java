
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Tour implements java.io.Serializable {

    private String companyName;
    private String hostCountry;
    private Date leaveDate;
    private Date arrivalDate;
    private float cost;
    private int counst;

    public Tour(String name, String hostCountry, String leave, String arrival, float cost, int counst) throws ParseException {
        this.companyName = name;
        this.hostCountry = hostCountry;
        this.leaveDate = toDate(leave);
        this.arrivalDate = toDate(arrival);
        this.cost = cost;
        this.counst = counst;
    }

    public Tour() throws ParseException {
        this("@test1", "@test2", "11/11/1991", "12/12/1992", 1232, 2);
    }

    private Date toDate(String s) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(s);
    }

    @Override
    public String toString() {
        return "Company: " + companyName + " Country: " + hostCountry + " Date(L|A) " + leaveDate + " | " + arrivalDate + ", Cost:" + cost + "$, Counst: " + counst;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    public void setHostCountry(String hostCountry) {
        this.hostCountry = hostCountry;
    }

    public void setLeaveDate(String leave) throws ParseException {
        this.leaveDate = toDate(leave);
    }

    public void setArrivalDate(String arrival) throws ParseException {
        this.arrivalDate = toDate(arrival);
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCounst(int counst) {
        this.counst = counst;
    }

}
