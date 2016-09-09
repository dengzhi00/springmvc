package com.yuansq.util;
/*package com.yuansq.test.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class game {
	public static String father = "爸爸";
	public static String mother = "妈妈";
	public static String son = "儿子";
	public static String daughter = "女儿";
	public static String dog = "�?";
	public static String steward = "管家";
//	public static List note = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("�?�?");

		List<String> origin = new ArrayList<>();
		origin.add(father);
		origin.add(mother);
		origin.add(son);
		origin.add(son);
		origin.add(daughter);
		origin.add(daughter);
		origin.add(dog);
		origin.add(steward);
		List<String> destination = new ArrayList<>();
		Map<String, String> ship = new HashMap<>();
		List<String> noteList = new ArrayList<>();
		System.out.println(checkDes(origin));
		 byShip(origin, destination, ship, noteList); 
		
		 * for (int i = 0; i < note.size(); i++) {
		 * System.out.println(note.get(i).toString()); }
		 
		System.out.println("结束");
	}

	// 1.俩人上船
	// 2.下一个人过来�?个人 （上�?个人或�?�不上）
	// 3.俩人上船
	// 4.下一个人过来�?个人（上�?个人或�?�不上）

	public static void byShip(List<String> origin, List<String> destination, Map<String, String> ship, List<String> list) {

		if (origin.size() == 1) {
			if (checkShip(origin.get(0), ship.get(0))) {
				System.out.println("成功�?");
				System.out.println(list.toString());
			} else {
				System.out.println("失败�?" + list.toString());
			}
		} else {
			if (destination.size() >= 2) {
				for (int i = 0; i < destination.size() - 1; i++) {
					for (int j = i + 1; j < destination.size(); j++) {
						if (checkShip(destination.get(i), destination.get(j))) {
							return;
						}
					}
				}
			}
			if (origin.size() >= 1 && ship.size() >= 1) {
				for (int i = 0; i < origin.size() - 1; i++) {
					if (checkShip(origin.get(i), ship.get(0))) {
						return;
					}
				}
			}

			for (int i = 0; i < origin.size(); i++) {
				// 上船
				ship.put(origin.get(i), "");
				if (ship.size() < 2) {

					origin.remove(i);
					byShip(origin, destination, ship, list);
				} else if (ship.size() == 2) {
					// 校验船上人员
					if (checkShip(ship.get(0), ship.get(1))) {
						Map<String, String> ship1 = ship;
						List<String> destination1 = destination;
						List<String> origin1 = origin;
						List<String> list1 = list;
						// �?

						if (ship.get(0).equals(mother) || ship.get(0).equals(father) || ship.get(0).equals(steward)) {
							list.add(ship.get(0) + ship.get(1));
							destination.add(ship.get(1));
							ship.remove(ship.size() - 1);
							origin.remove(i);

							byShip(origin, destination, ship, list);
						} else if (ship.get(1).equals(mother) || ship.get(1).equals(father)
								|| ship.get(1).equals(steward)) {
							list1.add(ship1.get(0) + ship1.get(1));
							destination1.add(ship1.get(0));
							ship1.remove(ship1.size() - 1);
							origin1.remove(i);
							byShip(origin, destination1, ship1, list1);
						}

					} else {
						ship.remove(ship.size() - 1);
					}
				}
			}
		}
		return;

	}

	public static boolean checkShip(String a, String b) {
		if (a.equals("father")) {
			if (b.equals(daughter))
				return false;
			if (b.equals(dog))
				return false;
		} else if (a.equals(mother)) {
			if (b.equals(son))
				return false;
			else if (b.equals(dog))
				return false;
		} else if (a.equals(son)) {
			if (b.equals(dog))
				return false;
			if (b.equals(mother))
				return false;
		} else if (a.equals(father)) {
			if (b.equals(daughter))
				return false;
			if (b.equals(dog))
				return false;
		} else if (a.equals(dog)) {
			if (!b.equals(steward))
				return false;

		} else if (a.equals(steward)) {
			return true;
		}
		return true;
	}

	public static boolean checkDes(List<String> des) {
		for (int i = 0; i < des.size() - 1; i++) {
			for (int j = i + 1; j < des.size(); j++) {
				if (des.get(i).equals(father)) {
					if (des.get(j).equals(daughter)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(mother)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					} else if (des.get(j).equals(dog)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(steward)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					}
				} else if (des.get(i).equals(mother)) {
					if (des.get(j).equals(son)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(father)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					} else if (des.get(j).equals(dog)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(steward)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					}

				} else if (des.get(i).equals(son)) {
					if (des.get(j).equals(mother)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(father)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					} else if (des.get(j).equals(dog)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(steward)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					}

				} else if (des.get(i).equals(daughter)) {
					if (des.get(j).equals(father)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(mother)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					} else if (des.get(j).equals(dog)) {
						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(steward)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						}
					}

				} else if (des.get(i).equals(dog)) {
					if (des.size() <= 1) {
						break;
					} else if (des.get(j).equals(steward)) {
						break;
					} else if (!des.get(j).equals(steward)) {

						boolean res = false;
						for (int k = 0; k < des.size(); k++) {
							if (des.get(k).equals(steward)) {
								res = true;
								break;
							}
						}
						if (res == false) {
							return false;
						} else if (res == true) {
							break;
						}

					}
				}
			}

		}
		;
		return true;
	}

}

 * Map<String, String> check1 = new HashMap<String, String>(); Map<String,
 * String> check2 = new HashMap<String, String>(); Map<String, String> check3 =
 * new HashMap<String, String>(); Map<String, String> check4 = new
 * HashMap<String, String>(); Map<String, String> check5 = new HashMap<String,
 * String>(); Map<String, String> check6 = new HashMap<String, String>();
 * Map<String, String> check7 = new HashMap<String, String>(); Map<String,
 * String> check8 = new HashMap<String, String>(); Map<String, String> check9 =
 * new HashMap<String, String>(); Map<String, String> check10 = new
 * HashMap<String, String>(); Map<String, String> check11 = new HashMap<String,
 * String>(); Map<String, String> check12 = new HashMap<String, String>();
 * check1.put(father, daughter); check2.put(daughter, father);
 * check3.put(mother, daughter); check4.put(daughter, mother); check5.put(dog,
 * father); check6.put(dog, mother); check7.put(dog, son); check8.put(dog,
 * daughter); check9.put(father, dog); check10.put(mother, dog);
 * check11.put(son, dog); check12.put(daughter, dog);
 
*/