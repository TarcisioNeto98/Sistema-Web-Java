package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DAO.FuncionarioDAO;
import model.Funcionario;

@WebServlet("/api/funcionarios/*")
public class FuncionarioService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FuncionarioService() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		if(pathInfo.contains("cargo")) {
			pathInfo = pathInfo.replace("/cargo/", "");
			ArrayList<Funcionario> lista = FuncionarioDAO.FuncionarioPorCargo(pathInfo);
			JSONArray jarray = new JSONArray();
			for(Funcionario f : lista) {
				JSONObject obj = new JSONObject();
				obj.put("id", f.getId());
				obj.put("nome", f.getNome());
				obj.put("cargo", f.getCargo());
				obj.put("dataNascimento", f.getDataAniversario());
				obj.put("dataEntrada", f.getDataEntrada());
				jarray.put(obj);
			}
			System.out.println(jarray.toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jarray.toString());
			response.getWriter().flush();
			return;
		}
		
		//GET BY ID
		
		if(pathInfo.contains("/id")) {
			pathInfo = pathInfo.replace("/id/", "");
			Funcionario funcionario = FuncionarioDAO.getFuncionarioId(Integer.parseInt(pathInfo));
			if(funcionario != null) {
				JSONObject obj = new JSONObject();
				obj.put("id", funcionario.getId());
				obj.put("nome", funcionario.getNome());
				obj.put("cargo", funcionario.getCargo());
				obj.put("dataNascimento", funcionario.getDataAniversario());
				obj.put("dataEntrada", funcionario.getDataEntrada());
					
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(obj.toString());
				response.getWriter().flush();
					
			}
			return;
		}
		System.out.println(pathInfo);
		if(pathInfo.contains("/quantidade")) {
			pathInfo = pathInfo.replace("/quantidade/", "");
			ArrayList<Funcionario> lista = FuncionarioDAO.FuncionarioPorQuantidade(Integer.parseInt(pathInfo));
			JSONArray funcJsonArray = new JSONArray();
			for(Funcionario f : lista) {
				JSONObject funcJson = new JSONObject();
				funcJson.put("id", f.getId());
				funcJson.put("nome", f.getNome());
				funcJson.put("cargo", f.getCargo());
				funcJson.put("dataNascimento", f.getDataAniversario());
				funcJson.put("dataEntrada", f.getDataEntrada());
				
				funcJsonArray.put(funcJson);
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(funcJsonArray.toString());
			response.getWriter().flush();
			return;
		}
		
		//GET ALL
		ArrayList<Funcionario> lista = FuncionarioDAO.getAllFuncionarios();
		JSONArray funcJsonArray = new JSONArray();
		
		for(Funcionario f : lista) {
			JSONObject funcJson = new JSONObject();
			funcJson.put("id", f.getId());
			funcJson.put("nome", f.getNome());
			funcJson.put("cargo", f.getCargo());
			funcJson.put("dataNascimento", f.getDataAniversario());
			funcJson.put("dataEntrada", f.getDataEntrada());
			
			funcJsonArray.put(funcJson);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(funcJsonArray.toString());
		response.getWriter().flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer buffer = new StringBuffer();
		String line = null;
		
		try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                buffer.append(line);
        } catch (Exception e) {
        }
		
		System.out.println(buffer.toString());
		JSONObject data = new JSONObject(buffer.toString());
		
		Funcionario f = FuncionarioDAO.addFuncionario(data.getString("nome"), data.getString("cargo"), data.getString("dataNascimento"), data.getString("dataEntrada"));
		
		data = new JSONObject();
		
		data.put("id", f.getId());
		data.put("nome", f.getNome());
		data.put("cargo", f.getCargo());
		data.put("dataNascimento", f.getDataAniversario());
		data.put("dataEntrada", f.getDataEntrada());
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(data.toString());
		response.getWriter().flush();
	}
	
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		String line = null;
		
		try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                buffer.append(line);
        } catch (Exception e) {
        }
		
		JSONObject obj = new JSONObject(buffer.toString());
		try {
			Funcionario funcionario = FuncionarioDAO.atualizarFuncionario(Integer.parseInt(obj.getString("id")), obj.getString("nome"),
			obj.getString("cargo"), obj.getString("dataNascimento"), obj.getString("dataEntrada"));
			
			obj = new JSONObject();
			
			obj.put("id", funcionario.getId());
			obj.put("nome", funcionario.getNome());
			obj.put("cargo", funcionario.getCargo());
			obj.put("dataNascimento", funcionario.getDataAniversario());
			obj.put("dataEntrada", funcionario.getDataEntrada());
			
		}catch(JSONException e) {
			e.printStackTrace();
		}
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(obj.toString());
		resp.getWriter().flush();
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getPathInfo() != null) {
			String parametro[] = req.getPathInfo().split("/");
			if(parametro.length > 0) {
				FuncionarioDAO.removerFuncionario(Integer.parseInt(parametro[1]));
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().flush();
			}
		}
	}

}

