package to.grindelf.apartmentmanager.domain;

public class Booking {

    private final int id;
    private final int apartmentId;
    private final String bookerName;
    private final String dateFrom;
    private final String dateTo;

    public Booking(int id, int apartmentId, String bookerName, String dateFrom, String dateTo) {
        this.id = id;
        this.apartmentId = apartmentId;
        this.bookerName = bookerName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public String getBookerName() {
        return bookerName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", apartmentId=" + apartmentId +
                ", bookerName='" + bookerName + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (id != booking.id) return false;
        if (apartmentId != booking.apartmentId) return false;
        if (!bookerName.equals(booking.bookerName)) return false;
        if (!dateFrom.equals(booking.dateFrom)) return false;
        return dateTo.equals(booking.dateTo);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + apartmentId;
        result = 31 * result + bookerName.hashCode();
        result = 31 * result + dateFrom.hashCode();
        result = 31 * result + dateTo.hashCode();
        return result;
    }

}
