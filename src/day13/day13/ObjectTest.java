package day13;

import java.util.Date;

public class ObjectTest {

	/*
	 * Object:
	 * 	1,所有类的根类
	 * 	2，类中未显式使用extends默认继承Object
	 * 	3，Object中的一切方法都有通用性
	 * 
	 * ==和equals的区别：
	 * 	==是运算符，可以用在基本数据类型和引用数据类型中,基本数据类型是比较数据是否相等，类型可以不同，应用数据类型则是比较地址值是否相等
	 *  equals是方法,，非运算符，只能适用于引用数据类型
	 *  	Object 中equals定义的方法和==是相同的
	 *  	String, Date,File，包装类等类型都重写了equals方法，重写之后不比较引用地址，而是比较内容
	 * 		equals的重写原则，比较两个实体的内容是否相同
	 * 		注意String new和直接写常量的区别，"BB" == "BB"是true，“BB”.equals(new String("BB"))是false
	 * 
	 * equals原则：
	 * 		对称性，两个互相调用的结果应该相同
	 * 		自反性：自己调用自己必须是true
	 * 		传递性：x-》y-》z
	 * 		一致性：不管重复多少次，结果都是一样的
	 * 		非空性：任何情况下，对null的结果都是false
	 * 		异类不相容性：和不同的类比较都是false
	 * 		类要equals,成员的自定义类也需要考虑equals
	 * 
	 * toString方法：
	 * 		直接输出对象实际上是调用了toString方法
	 * 		Object中的toString输出格式是类名加地址值（虚拟的，用hashcode算出来的）
	 * 		String，Date，File包装类都重写了toString方法
	 * 		可以重写toString方法
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OTest ot = new OTest();
		System.out.println(ot.getClass());
		ot = null;
		System.gc();
		System.out.println("------------");
		int i = 1;
		int j = 1;
		double d = 1.0;
		char c = 10;
		System.out.println(i == j);
		System.out.println(i == d);
		System.out.println(i == c);
		
		OTest o1 = new OTest();
		OTest o2 = new OTest();
		OTest o3 = o1;
		System.out.println(o1 == o2);
		System.out.println(o1 == o3);
		String s1 = new String("tt");
		String s2 = new String("tt");
		System.out.println(s1 == s2);
		
		String s3 = "bb";
		String s4 = "bb";
		System.out.println(s4 == s3);//常量池中直接复用了
		
		
		System.out.println("equals--------");
		System.out.println(o1.equals(o2));
		System.out.println(s1.equals(s2));
		Date d1 = new Date(1231231);
		Date d2 = new Date(1231231);
		System.out.println(d1.equals(d2));
		
		System.out.println("toString-------------");
		System.out.println(o1);
	}

}

class OTest{
	
	private int id;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("finalize");
		super.finalize();
		System.out.println("finalized");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override//自动生成，健壮性更好
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OTest other = (OTest) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	@Override//自动生成
	public String toString() {
		return "OTest [id=" + id + "]";
	}
	
	
	
//	@Override//手动实现
//	public String toString() {
//		// TODO Auto-generated method stub
//		return String.valueOf(this.id);
//	}
	
//	@Override//手动实现
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		if (this == obj) {
//			return true;
//		}
//		
//		if(obj instanceof OTest) {
//			OTest ot = (OTest)obj;
//			if(ot.getId() == this.getId()) {
//				return true;
//			}else {
//				return false;
//			}
//		}
//		return false;
//	}
	
	
	
}