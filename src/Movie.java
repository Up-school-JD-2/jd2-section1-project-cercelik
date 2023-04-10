import java.util.Arrays;

public class Movie {

	private int movieID;
	private final String movieName;
	private final String movieDirector;
	private final String movieReleaseDate;
	private final String description;
	private String[] genre;
	private String[] movieStars;
	private static int id = 1;

	public Movie(String movieName, String movieDirector, String movieReleaseDate, String description,
			String[] movieStars, String[] genre) {

		this.movieID = id++;
		this.movieName = movieName;
		this.movieDirector = movieDirector;
		this.movieReleaseDate = movieReleaseDate;
		this.description = description;
		this.movieStars = movieStars;
		this.genre = genre;

	}

	/**
	 * @return the movieID
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * @return the movieDirector
	 */
	public String getMovieDirector() {
		return movieDirector;
	}

	/**
	 * @return the movieReleaseDate
	 */
	public String getMovieReleaseDate() {
		return movieReleaseDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the movieStars
	 */
	public String[] getMovieStars() {
		return movieStars;
	}

	/**
	 * @return the genre
	 */
	public String[] getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		return "Movie [movieID=" + movieID + ", *movieName=" + movieName + ", *movieDirector=" + movieDirector
				+ ", *movieReleaseDate=" + movieReleaseDate + ", *description=" + description + ", *movieStars="
				+ Arrays.toString(movieStars) + "]";
	}

}
