import java.util.Arrays;

public class MovieStoreService {

	private Movie movieArr[] = new Movie[DomainConstants.MOVIE_OPENING_COUNT];
	private int movieCount;

	public void addMovie(String movieName, String movieDirector, String movieReleaseDate, String description,
			String movieStars, String genre) {

		if (movieCount == movieArr.length - 1) {
			reinitializeMovieArray();
		}

		String[] movieStarsArr = movieStars.trim().split("\\s*,\\s*");

		String[] genreArr = getMovieGenre(genre);

		movieArr[movieCount] = new Movie(movieName, movieDirector, movieReleaseDate, description, movieStarsArr,
				genreArr);
		movieCount++;
	}

	public void reinitializeMovieArray() {
		Movie[] newMovieArr = new Movie[movieArr.length + DomainConstants.MOVIE_OPENING_COUNT];
		System.arraycopy(movieArr, 0, newMovieArr, 0, movieArr.length);
		movieArr = newMovieArr;
	}

	public String[] reinitializeGenreArray(String movieGenre[]) {
		String[] newMovieGenre = new String[movieGenre.length + DomainConstants.GENRE_OPENING_COUNT];
		System.arraycopy(movieGenre, 0, newMovieGenre, 0, movieGenre.length);
		return newMovieGenre;
	}

	public Movie searchMovieByName(String movieName) {

		Movie searchedMovie = null;
		for (Movie m : movieArr) {

			if (m != null && m.getMovieName().equalsIgnoreCase(movieName)) {
				searchedMovie = m;
				break;
			}
		}
		return searchedMovie;
	}

	public String[] getMovieGenre(String genre) {

		int i = 0;
		String[] movieGenre = genre.trim().split("\\s*,\\s*");
		String[] newMovieGenre = new String[DomainConstants.GENRE_OPENING_COUNT];

		for (String mg : movieGenre) {
			for (MovieGenre g : MovieGenre.values()) {
				if (g.toString().equalsIgnoreCase(mg)) {
					if (i == newMovieGenre.length) {
						newMovieGenre = reinitializeGenreArray(newMovieGenre);
					}
					newMovieGenre[i] = g.toString();
					i++;
				}
			}
		}

		return newMovieGenre;
	}

	public void deleteMovie(String movieName) {

		Movie[] newMovieArr = new Movie[movieArr.length - 1];
		for (int i = 0, j = 0; i < movieArr.length - 1; i++) {
			if (movieArr[i] != null && !movieArr[i].getMovieName().equalsIgnoreCase(movieName)) {
				newMovieArr[j] = movieArr[i];
				j++;
			}
		}
		movieArr = newMovieArr;

	}

	public void printMovie() {
		for (int i = 0; i < movieArr.length; i++) {

			if (movieArr[i] == null) {
				return;
			}
			System.out.println("#Movie " + movieArr[i].getMovieID() + " -> " + "movie name: "
					+ movieArr[i].getMovieName() + " " + "movie director: " + movieArr[i].getMovieDirector() + " "
					+ "movie release date: " + movieArr[i].getMovieReleaseDate() + " " + "movie description: "
					+ movieArr[i].getDescription() + " " + "movie stars: "
					+ Arrays.toString(movieArr[i].getMovieStars()) + " " + "movie genre: "
					+ Arrays.toString(movieArr[i].getGenre()));
		}

	}

}
