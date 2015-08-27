/*Lyft Programming Challenge
 * Author: Bennett Schalich
 * 8/26/15
 */

/* Calculate the detour distance between two different rides. 
 * Given four latitude / longitude pairs, where driver one is 
 * traveling from point A to point B and driver two is traveling 
 * from point C to point D, write a function 
 * (in your language of choice) to calculate the shorter of the 
 * detour distances the drivers would need to take to pick-up 
 * and drop-off the other driver.
 */

/* My interpretation of this problem is to find the 
 * most optimal route the drivers should take to travel the
 * least distance.
 */

import java.awt.Point;
import java.util.*;

public class Driver
{

	public static void main(String[] args)
	{
		/* Use shortestRoute function to find the shortest route between the four points
		 * given the problem constraints
		 */
		List<Point> route = shortestRoute(new Point(0,0), new Point(1, 0), 
														new Point(2, 0), new Point(7, 0));
		
		/* Now that we have the shortest route we can compute the total route distance
		 */
		System.out.println(getTotalRouteDistance(route));
		
	}
	
	/*main function that calculates the shortest route and 
	 *	returns a List of points corresponding to that route 
	 */
	private static List<Point> shortestRoute(Point a, Point b, Point c, Point d)
	{
		/* By the definition of the problem there can 
		 * only two options a-c-d-b or c-a-b-d.
		 * The driver has to start a and end at b 
		 * or start at c and end at d while driving through the 
		 * exact route of the other driver (a-b or c-d)
		 * So we have below two pre-made lists of the only possibilities
		 */
		List<Point> route1 = new ArrayList<Point>(Arrays.asList(a, c, d, b));
		List<Point> route2 = new ArrayList<Point>(Arrays.asList(c, a, b, d));
		
		//Get the total distances of each route
		double sumR1 = getTotalRouteDistance(route1);
		double sumR2 = getTotalRouteDistance(route2);
		
		/* Because Java doesn't support tuples we will have to return
		 * only the route. We can recompute the total route distance in main 
		 */
		if (sumR1 <= sumR2)
			return route1;
		else
			return route2;
	}
	
	//Calculates the total distance of each route and returns the distance
	private static double getTotalRouteDistance(List<Point> route)
	{
		double sum = 0;
		
		for (int i = 0; i < route.size() - 1; i++)
		{
			sum += getDistance(route.get(i), route.get(i + 1));
		}
		
		return sum;
	}
	
	//Calculates the distance between two points and returns it
	private static double getDistance(Point x, Point y)
	{
		double a = x.getX() - y.getX();
		double b = x.getY() - y.getY();
		return Math.sqrt((a * a) + (b * b));
	}
}
