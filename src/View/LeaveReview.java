package View;

public class LeaveReview extends BaseMenu {
    private final Movie movie;

    public LeaveReview(BaseMenu previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }

}
