import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Funcionario;

public class FuncionarioDAO {
	private static int i = 3;
	private static Map <Integer, Funcionario> MapFuncionario = new HashMap<Integer, Funcionario>();

	static{
		init();
	}
	
	private static void init() {
		Funcionario f1 = new Funcionario(0, "tarcisio", "Chefe", "21/06/1998", "21/06/2020");
		Funcionario f2 = new Funcionario(1, "Tarcisio", "Chefe", "21/06/1998", "21/06/2020");
		Funcionario f3 = new Funcionario(2, "Tarcisio", "Chefe", "21/06/1998", "21/06/2020");
		MapFuncionario.put(f1.getId(), f1);
		MapFuncionario.put(f2.getId(), f2);
		MapFuncionario.put(f3.getId(), f3);
	}
	
	public static ArrayList getAllFuncionarios() {
		return new ArrayList<Funcionario>(MapFuncionario.values());
	}
}
