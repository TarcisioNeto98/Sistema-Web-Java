package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	public static ArrayList<Funcionario> getAllFuncionarios() {
		return new ArrayList<Funcionario>(MapFuncionario.values());
	}
	
	public static Funcionario addFuncionario(String nome, String cargo, String dataNascimento, String dataEntrada) {
		Funcionario funcionario = new Funcionario(i, nome, cargo, dataNascimento, dataEntrada);
		MapFuncionario.put(funcionario.getId(), funcionario);
		i++;
		return funcionario;
	}
	
	public static Funcionario getFuncionarioId(int id) {
		if(MapFuncionario.containsKey(id)) return MapFuncionario.get(id);
		return null;
	}
	
	public static void removerFuncionario(int id) {
		if(MapFuncionario.containsKey(id)) {
			MapFuncionario.remove(id);
		}
	}
	
	public static Funcionario atualizarFuncionario(int id, String nome, String cargo, String dataNascimento, String dataEntrada) {
		Funcionario f1 = new Funcionario(id, nome, cargo, dataNascimento, dataEntrada);
		MapFuncionario.put(f1.getId(), f1);
		return f1;
	}
	
	public static ArrayList<Funcionario> FuncionarioPorCargo(String cargo){
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		Set<Integer> chaves = MapFuncionario.keySet();
		
		for(int chave : chaves) {
			if(MapFuncionario.get(chave).getCargo().equals(cargo)) {
				lista.add(MapFuncionario.get(chave));
			}
		}
		return lista;
	}
	
	public static ArrayList<Funcionario> FuncionarioPorQuantidade(int quantidade){
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
		Set<Integer> chaves = MapFuncionario.keySet();
		int cont = 0;
		for(int chave : chaves) {
			if(cont < quantidade) {
				lista.add(MapFuncionario.get(chave));
				cont++;
			}
			else break;
		}
		return lista;
	}
}
