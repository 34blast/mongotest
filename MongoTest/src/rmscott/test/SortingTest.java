/**
 * RMSCOTT Prototype
 */
package rmscott.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import rmscott.common.BaseBean;
import rmscott.common.BaseBeanComparator;
import rmscott.common.Person;
import rmscott.common.PersonComparator;
import rmscott.football.FootballPosition;
import rmscott.football.Player;
import rmscott.football.PlayerComparator;
import rmscott.util.KeyGenerator;

/**
 * @author rmscott
 *
 */
public class SortingTest {

	/**
	 * Constructor for the class
	 */
	public SortingTest() {

	}

	static public BaseBean[] getAllInitialBeans() {

		ArrayList<BaseBean> list = new ArrayList<BaseBean>();

		BaseBean[] baseBeans = SortingTest.getInitialBaseBeans();
		if (baseBeans != null) {
			for (BaseBean bean : baseBeans) {
				list.add(bean);
			}
		}
		Person[] persons = SortingTest.getInitialPersons();
		if (persons != null) {
			for (BaseBean bean : persons) {
				list.add(bean);
			}
		}

		Player[] players = SortingTest.getInitialPlayers();
		if (players != null) {
			for (BaseBean bean : players) {
				list.add(bean);
			}
		}

		BaseBean[] returnBeans = list.toArray(new BaseBean[list.size()]);

		return returnBeans;

	} // end of getInitialPlayers

	static public BaseBean[] getInitialBaseBeans() {

		BaseBean base1 = new BaseBean();
		base1.set_id(KeyGenerator.generateKey());

		BaseBean base2 = new BaseBean();
		base2.set_id(KeyGenerator.generateKey());

		BaseBean[] beans = new BaseBean[2];
		beans[0] = base1;
		beans[1] = base2;

		return beans;

	} // end of getInitialBaseBeans

	static public Person[] getInitialPersons() {

		Person person1 = new Person();
		person1.set_id(KeyGenerator.generateKey());
		person1.setFirstName("Richard");
		person1.setLastName("Scott");
		person1.setNickName("R.M.");

		Person person2 = new Person();
		person2.set_id(KeyGenerator.generateKey());
		person2.setFirstName("Richard");
		person2.setMiddleName("Wilson");
		person2.setLastName("Scott");
		person2.setNickName("Scotty");

		Person[] beans = new Person[2];
		beans[0] = person1;
		beans[1] = person2;

		return beans;

	} // end of getInitialPersons

	static public Player[] getInitialPlayers() {

		Player marvinJones = new Player();
		marvinJones.set_id(KeyGenerator.generateKey());
		marvinJones.setFirstName("Marvin");
		marvinJones.setMiddleName("");
		marvinJones.setLastName("Jones");
		marvinJones.setNotes("Average Reciever");
		marvinJones.setRanking((float) 77.5);
		marvinJones.setPosition(FootballPosition.WR);
		
		Player odellBeckum = new Player();
		odellBeckum.set_id(KeyGenerator.generateKey());
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever,");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition(FootballPosition.WR);

		Player dezBryant = new Player();
		dezBryant.set_id(KeyGenerator.generateKey());
		dezBryant.setFirstName("Dez");
		dezBryant.setLastName("Bryant");
		dezBryant.setNotes("Elite Reciever");
		dezBryant.setRanking((float) 85.3);
		dezBryant.setPosition(FootballPosition.WR);

		Player ajGreen = new Player();
		ajGreen.set_id(KeyGenerator.generateKey());
		ajGreen.setFirstName("Adriel");
		ajGreen.setMiddleName("Jeremiah");
		ajGreen.setLastName("Green");
		ajGreen.setNotes("Elite Reciever");
		ajGreen.setRanking((float) 91.3);
		ajGreen.setPosition(FootballPosition.WR);

		Player[] beans = new Player[4];
		beans[0] = odellBeckum;
		beans[1] = dezBryant;
		beans[2] = ajGreen;
		beans[3] = marvinJones;

		return beans;

	} // end of getInitialPlayers

	static public BaseBean[] orderByBaseBean(BaseBean[] pBaseBeans) {
		Arrays.sort(pBaseBeans);
		return pBaseBeans;
	}

	static public BaseBean[] orderByBaseBeanComparator(BaseBean[] pBaseBeans) {

		Arrays.sort(pBaseBeans, new BaseBeanComparator());
		return pBaseBeans;
	}

	static public BaseBean[] orderByPersonComparator(Person[] pBaseBeans) {

		Arrays.sort(pBaseBeans, new PersonComparator());
		return pBaseBeans;
	}

	static public BaseBean[] orderByPlayerComparator(Player[] pBaseBeans) {

		Arrays.sort(pBaseBeans, new PlayerComparator());
		return pBaseBeans;
	}

	static public Player[] orderByPlayerRankingsInline(Player[] pPlayers) {

		Arrays.sort(pPlayers, new Comparator<Player>() {
			public int compare(Player pOne, Player pTwo) {
				Float one = new Float(pOne.getRanking());
				Float two = new Float(pTwo.getRanking());
				return two.compareTo(one);
			}
		});

		return pPlayers;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("PlayerCRUDTest.main() : ..... begin execution ..... ");
		System.out.println();

		BaseBean[] origBeans = SortingTest.getAllInitialBeans();
		BaseBean[] newBeans = null;

		System.out.println("Original BaseBean List");
		System.out.println("---------------------------------------------------------------");
		for (BaseBean bean : origBeans) {
			System.out.println(bean);
		}
		System.out.println();

		System.out.println("Sorted all beans by compareTo");
		System.out.println("---------------------------------------------------------------");
		newBeans = SortingTest.orderByBaseBean(origBeans);
		for (BaseBean bean : newBeans) {
			System.out.println(bean);
		}
		System.out.println();

		System.out.println("Sorted all Beans by Comparator");
		System.out.println("---------------------------------------------------------------");
		newBeans = SortingTest.orderByBaseBeanComparator(origBeans);
		for (BaseBean bean : newBeans) {
			System.out.println(bean);
		}
		System.out.println();

		System.out.println("Sorted the Person by Person Comparator");
		System.out.println("---------------------------------------------------------------");
		newBeans = SortingTest.orderByPersonComparator(SortingTest.getInitialPersons());
		for (BaseBean bean : newBeans) {
			System.out.println(bean);
		}
		System.out.println();

		System.out.println("Sorted the Players By Player Comparator");
		System.out.println("---------------------------------------------------------------");

		newBeans = SortingTest.orderByPlayerComparator(SortingTest.getInitialPlayers());
		for (BaseBean bean : newBeans) {
			System.out.println(bean);
		}
		System.out.println();

		System.out.println("Sorted the Players By Player Comparator Inline");
		System.out.println("---------------------------------------------------------------");

		newBeans = SortingTest.orderByPlayerRankingsInline(SortingTest.getInitialPlayers());
		for (BaseBean bean : newBeans) {
			System.out.println(bean);
		}
		System.out.println();

		System.out.println("PlayerCRUDTest.main() : ..... ending execution ..... ");
		System.exit(0);
		;

	}

}
