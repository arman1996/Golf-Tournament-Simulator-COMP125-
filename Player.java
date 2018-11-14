package comp125;

import java.util.Random;

/**
 * Class to store family name, handicap and scores
 * for a golf player.
 * Several methods are incomplete. 
 * Part of your task for Assign 2 is to complete them,
 * and answer Question P1.
 * @author Scott McCallum, September 2016
 * Completed by: 
 */

public class Player {
	// attributes of player
	private String name;  // family name
	private int handicap;  // a golfing term (the number of strokes
	                       // which player is likely to make
	                       // above course par)
	private int [] scores; // scores for all holes in the course
	
	// Random generator belongs to class as a whole, 
	// so it is designated static.
	private static Random randomGenerator = new Random();
	
	// Returns a random variation from par for a given hole.
	// Question P1. Suppose that handicap = 18.
	// What are the minimum and maximum values that method variation()
	// can return?
	
	/* 
	 * Answer to P1:
	 * Largest value the "nextInt(90)" method will return is 89.
	 * And the smallest possible value that the "nextInt(90)" will generate is 0.
	 * So this variation() method will return (89 + 18)/18 - 2 = 5.9 - 2 = 5 - 2 = 3;
	 * And the lowest possible number that this method will return is (0 + 18)/18 - 2 = -1;
	 */
	private int variation() {
		int randomNumber = randomGenerator.nextInt(90);
		return (randomNumber + handicap)/18 - 2;
	}
	
	public static final int NUMHOLES = 18;  // NUMHOLES is constant.
	
	/**
	 * Set name.
	 * @param aName
	 */
	public void setName(String aName) {
		name = aName;
	}
	
	/**
	 * Set handicap.
	 * @param aHandicap
	 */
	public void setHandicap(int aHandicap) { 
			handicap = aHandicap;
	}
	
	/**
	 * Set scores.
	 * @param someScores
	 */
	public void setScores(int [] someScores) {
		for (int i = 0; i < someScores.length; i++)
			scores[i] = someScores[i];
	}
	
	/**
	 * Default constructor.
	 */
	public Player() {
		setName("Smith");
		setHandicap(0);
		scores = new int[NUMHOLES];
	}
	
	/**
	 * Constructor with two parameters.
	 */
	public Player(String aName, int aHandicap) {
		setName(aName);
		setHandicap(aHandicap);
		scores = new int[NUMHOLES];
	}
	/**
	 * Constructor with three parameters.
	 */
	public Player(String aName, int aHandicap, int [] someScores) {
		setName(aName);
		setHandicap(aHandicap);
		scores = new int[NUMHOLES];
		setScores(someScores);
	}
	
	public String getName() {
		return name;
	}
	
	public int getHandicap() {
		return handicap;
	}

	public int [] getScores() {
		return scores;
	}
	
	/**
	 * Return true if the two arrays a and b have exactly the same elements
	 * in the same order, false otherwise.
	 * @param a
	 * @param b
	 * @return true if the two arrays a and b have exactly the same elements
	 * in the same order, false otherwise.
	 */
	public static boolean equalArrays(int [] a, int [] b) {
		if((a == null) || (b == null)){
			return false;
		}
		if(a.length != b.length){
			return false;
		}
		else{
			for(int i = 0; i < a.length; i++){
				if(a[i] != b[i]){
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Return true if this player has exactly the same
	 * attributes as other player, false otherwise.
	 * Call String method equals and Player method equalArrays.
	 * @param other
	 * @return true if this player has exactly the same attributes
	 * as other player, false otherwise.
	 */
	public boolean equals(Player other) {
		boolean sameName;
		String thisName = this.getName();
		String otherName = other.getName();
		
		if(thisName.length() != otherName.length()){
			return false;
		}
		else{
			for(int i = 0; i < thisName.length(); i++){
				if(thisName.charAt(i) != otherName.charAt(i)){
					return false;
				}
			}
			sameName = true;
		}
		
		
		boolean sameScore;
		int [] thisScore = this.scores;
		int [] otherScore = other.scores;
		
		if(thisScore == null || otherScore == null){
			return false;
		}
		if(thisScore.length != otherScore.length){
			return false;
		}
		else{
			for(int i = 0; i < thisScore.length; i++){
				if(thisScore[i] != otherScore[i]){
					return false;
				}
			}
			sameScore = true;
		}
		
		
		boolean sameHandicap;
		
		if(this.handicap != other.handicap){
			return false;
		}
		sameHandicap = true;
		
		
		if(sameName == true && sameScore == true && sameHandicap == true){
			return true;
		}
		return false;
	}

	/**
	 * Return appropriate comment for given score and par for hole.
	 * You can assume that score is not less than 4 below par for hole.
	 * For example, if score is 4 below par, return "condor!!!!",
	 * if score is 3 below par, return "albatross!!!",
	 * if score is 2 below par, return "eagle!!", etc.
	 * See Assign 2 description for full list of comments.
	 * @param score
	 * @param parForHole
	 * @return appropriate comment for given score and par for hole.
	 */
	public static String comment(int score, int parForHole) {
		if(score - parForHole == - 4){
			return "condor!!!!";
		}
		else if(score - parForHole == -3){
			return "albatross!!!";
		}
		else if(score - parForHole == -2){
			return "eagle!!";
		}
		else if(score - parForHole == -1){
			return "birdie!";
		}
		else if(score == parForHole){
			return "par";
		}
		else if(score - parForHole == 1){
			return "bogey";
		}
		else if(score - parForHole == 2){
			return "double bogey";
		}
		else{
			return "triple+ bogey";
		}
	}
	
	/**
	 * Display the player's name and scores.
	 * For each score, provide an appropriate comment.
	 * @param par
	 */
	public void showScores(int [] par) {
		System.out.println("Scores for " + name);
		int score, parForHole;
		for (int i = 1; i <= scores.length; i++) {
			score = scores[i-1];
			System.out.print(i + "   " + score + "   ");
			parForHole = par[i-1];
			System.out.println(comment(score, parForHole));
		}
	}
	
	/**
	 * Return total score for player.
	 * @return total score for this player.
	 */
	public int totalScore() {
		int total = 0;
		int [] thisScore = this.scores;
		if(thisScore == null){
			return -1;
		}
		if(scores.length == 0){
			return 0;
		}
		/*
		 * Store the scores at each hole with the for loop
		 */
		for(int i = 0; i < thisScore.length; i++){
			total = total + thisScore[i];
		}
		return total;
	}
	
	/** 
	 * Return score for first n holes for player. Use recursion.
	 * You can assume that 0 <= n <= NUMHOLES.
	 * @param n
	 * @return score for first n holes for this player.
	 */
	public int totalScoreRec(int n) {
		int [] thisScore = this.scores;
		if(thisScore == null){
			return -1;
		}
		if(n == 0){
			return 0;
		}
		/*
		 * Call the recursive method again.
		 * Add the "score" at the current "n" value.
		 */
		else{
			return totalScoreRec(n - 1) + thisScore[n - 1];
		}
	}
	
	/**
	 * Simulate round of golf for this player.
	 * For each hole, the score is obtained by adding
	 * variation() to par for the hole.
	 * @param par
	 */
	public void play(int [] par) {
		for(int i = 0; i < par.length; i++){
			scores[i] = variation() + par[i];
		}
	}

	public static void main(String [] args) {
		int [] par = {3,4,5,4,5,3,4,3,5,3,4,5,4,3,4,5,4,3};
		int [] myScores = {3,4,3,5,3,4,4,3,5,3,3,4,3,4,3,4,3,4};
		Player myPlayer = new Player("Norman", 2, myScores);
		myPlayer.showScores(par);
		System.out.println("Total score is " + myPlayer.totalScore());
		Player player1 = new Player("Ogilvy", 6);
		player1.play(par);
		player1.showScores(par);
		System.out.println("Total score is " + player1.totalScore());
		Player player2 = new Player("Scott", 2);
		player2.play(par);
		player2.showScores(par);
		System.out.println("Total score is " + player2.totalScore());
		System.out.println(myPlayer.equals(player1));
		System.out.println(myPlayer.equals(myPlayer));
	}
}
