package comp125;

import java.util.ArrayList;

/**
 * Class to store the players in a golf tournament.
 * Several methods are incomplete.
 * Part of your task for Assign 2 is to complete them,
 * and answer Questions T1, T2 and T3.
 * @author Scott McCallum, September 2016
 * Completed by:
 */

public class Tournament {
	
	private int [] par; // stores par for each hole in the golf course.
	private ArrayList<Player> players; // stores the list of players.
	
	public static final int NUMHOLES = 18;  // constant.

	/**
	 * Set par.
	 * @param thePar
	 */
	public void setPar(int [] thePar) {
		for (int i = 0; i < par.length; i++)
			par[i] = thePar[i];
	}
	
	/**
	 * Constructor with one parameter.
	 * @param thePar
	 */
	public Tournament(int [] thePar) {
		par = new int[NUMHOLES];
		setPar(thePar);
		players = new ArrayList<Player>();
	}
	
	public int [] getPar() {
		return par;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Enter a player with given name and handicap into the tournament.
	 * @param aName
	 * @param aHandicap
	 */
	public void enter(String aName, int aHandicap) {
		Player temporary = new Player(aName, aHandicap);
		players.add(temporary);
	}
	
	/**
	 * Enter a player with given name, handicap and scores into tournament.
	 * @param aname
	 * @param aHandicap
	 * @param someScores
	 */
	public void enter(String aName, int aHandicap, int [] someScores) {
		Player temporary = new Player(aName, aHandicap, someScores);
		players.add(temporary);
	}
	
	/**
	 * Simulate playing a round of golf for all the players
	 * in the tournament.
	 * Call method play(..) of class Player.
	 */
	public void playRound() {
		
		for(int i = 0; i < players.size(); i++){
			players.get(i).play(par);
		}
	}

	/**
	 * Display list of players, each with his or her total score.
	 */
	public void showPlayers() {
		Player thePlayer;
		for (int i = 0; i < players.size(); i++) {
			thePlayer = players.get(i);
			System.out.printf("%-15s", thePlayer.getName());
			System.out.printf("%5d", thePlayer.totalScore());
			System.out.println();
		}
	}

	/*
	 * Assume as precondition that the list of players is not empty.
	 * Returns the winning score, that is, the lowest total score.
	 * @return winning score
	 */
	public int winningScore() {
		if(players == null){
			return -1;
		}
		/*
		 * Initialize the player.
		 * Store his total score.
		 */
		Player thePlayer = players.get(0);
		int result = thePlayer.totalScore();
		for(int i = 1; i < players.size(); i++){
			/*
			 * Make instances of the other player that we are comparing.
			 */
			Player otherPlayer = players.get(i);
			int otherResult = otherPlayer.totalScore();
			if(otherResult < result){
				result = otherResult;
				thePlayer = otherPlayer;
			}
		}
		return result;
	}
	
	/**
	 * Returns the list of winners, that is, the names of those players
	 * with the lowest total score.
	 * The winners' names should be stored in the same order as they occur
	 * in the tournament list.
	 * If there are no players, return empty list.
	 * @return list of winners' names
	 */
	public ArrayList<String> winners() {
		ArrayList<String> result = new ArrayList<String>();
		if(players.isEmpty()){
			return result;
		}
		int winScore = winningScore();
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).totalScore() == winScore){
				result.add(players.get(i).getName());
			}
		}
		return result;
	}
	
	/**
	 * Auxiliary method to insert players[i] into the sorted region
	 * players[0..i-1].
	 * You may choose to implement this method.
	 * (This method itself will not be directly tested.)
	 * @param i
	 */
	
	/*
	 * Goes backwards through the sorted region
	 * Keeps shuffling values along until it finds a value that it, itself is greater than
	 * Loop stops and then inserts player into the space created.
	 */
	public void insertIntoSortedRegion(int i) {
		Player copy = players.get(i);
		String copiedName = copy.getName();
		while(i > 0 && copiedName.compareTo(players.get(i - 1).getName()) < 0){
			players.set(i, players.get(i - 1));
			i --;
		}
		players.set(i, copy);
		// Supply this code if you wish.
	}
	
	/**
	 * Sorts the list of players alphabetically by name.
	 * Adapt insertion sort algorithm.
	 * You can assume that no two players have the same name.
	 * Question T1. Adapting insertion sort for this method
	 * could yield efficiencies relative to some other approaches
	 * for some important special cases.
	 * Do you agree and if so why? Write about 6 to 10 lines.
	 */
	/*
	 * Answer to T1:
	 * I agree with insertion sort being more efficient.
	 * Since we have the "equals" method we can use it to not get two of the same players.
	 * This eliminates the "worst case" scenario for insertion sort.
	 * Insertion sort has the least number of computations.
	 * Since the comparison is done in a helper method, the work load is simplified.
	 * Any other sorting method would have had far more computations.
	 * Insertion sort stops as soon as it encounters an element that it itself is greater than.
	 * And it inserts itself after it.
	 * In other sorting algorithms, each time a value needs to be inserted, the entire unsorted region needs to be searched.
	 * This avoids doing that.
	 * Comparing strings also made it easy since we could use the "compareTo" method.
	 */
	public void alphabeticSort() {
		for(int i = 1; i < players.size(); i++){
			insertIntoSortedRegion(i);
		}
	}
	
	public static int stringCompare(String a, String b){
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) < b.charAt(i)){
				return -1;
			}
			else if(a.charAt(i) > b.charAt(i)){
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * Sorts the first n elements of the
	 * list of players alphabetically by name. Use recursion.
	 * Adapt recursive insertion sort algorithm.
	 * You can assume that no two players have the same name.
	 * You can also assume that 0 <= n <= players.size().
	 */
	public void alphabeticSortRec(int n) {
		if( n == 1){
			return;
		}
		alphabeticSortRec(n - 1);
		insertIntoSortedRegion(n);
	}
	
	/**
	 * Assume as precondition that the list of players has been
	 * sorted alphabetically by name.
	 * Returns the total score of the player whose name is passed in.
	 * If player is not in tournament, return -1.
	 * Adapt binary search algorithm.
	 * Question T2. Adapting binary search for this method could yield
	 * efficiencies relative to some other approaches.
	 * Do you agree and if so why? Write about 6 to 10 lines.
	 * @return total score of given player.
	 */
	/*
	 * Answer to T2:
	 * Since the list of players is sorted by name and we know that each player has a unique name
	 * we can make an instance of the player with the score we want to find. This gives us access to
	 * that players name. We can then use this and "compareTo" method to govern our search.
	 * This is more effective than looking for the total score and trying to find players with the winning
	 * score. Binary search is a lot faster than any other search. Especially when the comparing conditions
	 * are as simple as 1, -1 and 0.
	 */
	public int findScore(String aName) {
		int first = 0;
		int last = players.size() - 1;
		while(first <= last){
			int median = (first + last)/2;
			if(stringCompare(players.get(median).getName(), aName) == 0){
				return players.get(median).totalScore();
			}
			else if(stringCompare(players.get(median).getName(), aName) == 1){
				last = median - 1;
			}
			else{
				first = median + 1;
			}
		}
		return -1;
	}
	
	public ArrayList<Player> clone(){
		ArrayList<Player> clone = new ArrayList<Player>();
		for(int i = 0; i < players.size(); i++){
			clone.add(players.get(i));
		}
		return clone;
	}
	
	/**
	 * Returns ranking of players with winners coming first, etc.
	 * Equally ranked players should be listed in the same
	 * order as they occur in the tournament list.
	 * Adapt selection sort algorithm.
	 * Question T3. Adapting selection sort for this method could yield
	 * efficiencies relative to some other approaches for some
	 * important special cases. Do you agree and if so why?
	 * Write about 6 to 10 lines.
	 * @return ranking of players
	 */
	/*
	 * Answer to T3:
	 * I agree that selection sort if the best way to do this even though it has far more computations than
	 * insertion sort. Since we want to display the names of the players in the order that they occur in,
	 * selection sort is the best one for the job. It keeps finding the minimum in the unsorted region and
	 * places it at the end of the sorted region. That is exactly how we want it. So that if there are two
	 * players with the same score, then they will occur together but in the same order that they entered the
	 * tournament.
	 */
	public ArrayList<Player> ranking() {
		ArrayList<Player> result = clone();
		int currentIndex = 0;
		int currentMin;
		
		for(int i = 0; i < result.size(); i++){
			currentIndex = i;
			currentMin = result.get(i).totalScore();
			for(int j = i + 1; j < result.size(); j++){
				if(result.get(j).totalScore() < currentMin){
					currentMin = result.get(j).totalScore();
					currentIndex = j;
				}
			}
			Player tmp = result.get(currentIndex);
			result.set(currentIndex, result.get(i) );
			result.set(i, tmp);
			//result.add(result.get(currentIndex));
			//players.set(currentIndex, result.get(i));
		}
		return result;
	}
	
	/**
	 * Carry out simple test of Tournament class.
	 * Add a few lines to test methods findScore and ranking.
	 */
	 
	public static void main(String[] args) {
		System.out.println("Welcome to the golf tournament program.");
		int [] par = {3,4,5,4,5,3,4,3,5,3,4,5,4,3,4,5,4,3};
		Tournament T = new Tournament(par);
		T.enter("Norman", 2);
		T.enter("Scott", 4);
		T.enter("Day", 1);
		T.enter("Aay", 1);
		T.enter("Bay", 1);
		T.enter("Cay", 1);
		T.playRound();
		T.showPlayers();
		System.out.println("Winning score is " + T.winningScore());
		System.out.println("The winners are:");
		ArrayList<String> winners = T.winners();
		for (int i = 0; i < winners.size(); i++)
			System.out.println(winners.get(i));
		T.alphabeticSort();
		T.showPlayers();
		
		for(Player i : T.ranking()) {
			System.out.println(i.getName() + "  " + i.totalScore());
		}
		
		System.out.println(T.findScore("Day"));
	}

}
