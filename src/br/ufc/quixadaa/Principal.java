package br.ufc.quixadaa;
import java.util.List;
import java.util.Scanner;
import br.ufc.quixada.dao.ClienteJDBCDAO;
import br.ufc.quixada.dao.FilmeJDBCDAO;
import br.ufc.quixada.dao.LocacaoJDBCDAO;
import br.ufc.quixada.entity.Cliente;
import br.ufc.quixada.entity.Filme;
import br.ufc.quixada.entity.Locacao;

import java.lang.Iterable;

@SuppressWarnings("unused")
public class Principal {
	public static Scanner entrada = new Scanner(System.in);
	public static void listarCliente(List<Cliente> listac) {
		if(listac.size()==0) {
			System.out.println("A Lista de Clientes Está Vazia!\n");
		}
		else {
			for(Cliente c: listac) {
				System.out.println(c.toString());
			}
		}
}
	
	public static void listarFilme(List<Filme> listaf) {
		if (listaf.size() ==0) {
			System.out.print("A Lista de Filmes Está Vazia!\n");
		}
		else {
			for(Filme f: listaf) {
				System.out.println(f.toString());
			}
		}
}
	
	public static void listarLocacao(List<Locacao> listal) {
		if (listal.size() ==0) {
			System.out.print("A Lista de Locações Está Vazia!\n");
		}
		else {
			for(Locacao l: listal) {
				System.out.println(l.toString());
			}
		}
}
	
	public static void menu() {
		System.out.println("OPÇÕES PARA CLIENTES:");
		System.out.println("1 - Inserir cliente");
		System.out.println("2 - Deletar cliente por cpf");
		System.out.println("3 - Buscar cliente por cpf");
		System.out.println("4 - Listar todos os clientes");
		System.out.println("5 - Atualizar cliente pelo cpf");
		System.out.println("--------------------------------------------\n");
		System.out.println("OPÇÕES PARA FILMES:");
		System.out.println("6 - Inserir filme");
		System.out.println("7 - Deletar filme pelo id");
		System.out.println("8 - Buscar filme pelo id");
		System.out.println("9 - Listar todos os filmes");
		System.out.println("10 - Atualizar filme pelo id");
		System.out.println("--------------------------------------------\n");	
		System.out.println("OPÇÕES PARA ALOCAÇÕES");
		System.out.println("11 - Alugar filme");
		System.out.println("12 - Listar filmes de um cliente");
		System.out.println("13 - Remover filme de um cliente");
		System.out.println("14 - Listar todos os filmes alugados");
		
	}
	
	/*esse metodo é usado para a criacao de clientes, sempre que ele é chamado(invocado) é 
	  criado internamente uma instancia da classe Cliente e logo depois sao exigidos a 
	  entrada dos valores de cada atributo via teclado e no final o metodo retorna o Cliente criado
	 */
	public static Cliente criarCliente() {
		Cliente c = new Cliente();
		System.out.println("CPF:  ");
		String cpf= entrada.nextLine();
		System.out.println("Nome: ");
		String nome= entrada.nextLine();
		System.out.println("Idade: ");
		
		int idade = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Sexo: ");
		char sexo= entrada.nextLine().charAt(0);
		c.setCpf(cpf);
		c.setNome(nome);
		c.setIdade(idade);
		c.setSexo(sexo);
		return c;
	}
	
	/*esse metodo é usado para a criacao de filmes, sempre que ele é chamado(invocado) é 
	  criado internamente uma instancia da classe Filme e logo depois sao exigidos a 
	  entrada dos valores de cada atributo via teclado e no final o metodo retorna o Filme criado
	 */
	public static Filme criarFilme() {
		Filme f = new Filme();
		System.out.println("Id: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Nome: ");
		String nome = entrada.nextLine();
		System.out.println("Gênero: ");
		String genero = entrada.nextLine();
		f.setId(id);
		f.setNome(nome);
		f.setGenero(genero);
		return f;
	}
	
	//TO DO
	public static Locacao criarLocacao() {
		Locacao l = new Locacao();
		System.out.println("Id Do Filme: ");
		int id_filme = entrada.nextInt();
		entrada.nextLine();
		System.out.println("CPF Do cliente: ");// TRY
		String cpf_cliente = entrada.nextLine();
		System.out.println("Data Da Locação: ");
		String data_locacao = entrada.nextLine();
		System.out.println("Data Da Devolução: ");
		String data_devolucao = entrada.nextLine();
		l.setId_filme(id_filme);
		l.setCpf_cliente(cpf_cliente);
		l.setData_locacao(data_locacao);
		l.setData_devolucao(data_devolucao);
		return (Locacao) l;
	}
	
	public static void main(String[] args){
		ClienteJDBCDAO baseCliente = new ClienteJDBCDAO();
		FilmeJDBCDAO baseFilme = new FilmeJDBCDAO();
		LocacaoJDBCDAO baseLocacao = new LocacaoJDBCDAO();
		String opcao="0";
		
		do {
			Principal.menu();
			opcao=entrada.nextLine();
			switch (opcao) {
			case "1": //inserir cliente
				Cliente c=Principal.criarCliente();
				if(baseCliente.find(c.getCpf())==null && baseCliente.adicionar(c)) {
					System.out.println("Cliente inserido com sucesso.\n");
				}
				else if (baseCliente.find(c.getCpf())!=null) {
					System.out.println("ja existe um Cliente com esse cpf.\n");
				}
				else {
					System.out.println("Erro: proibido inserir cliente menor de 18 anos!\n");
				}
				break;
			case "2"://Deletar funcionario pelo cpf
				System.out.println("CPF: ");
				String cpf=entrada.nextLine();
				if(baseCliente.remover(cpf)) {
					System.out.println("Cliente removido com sucesso.\n");
				}
				else {
					System.out.println("Nao foi possivel remover o Cliente.\n");
				}
				break;
				
			case "3"://buscar Cliente por cpf
				System.out.println("CPF: ");
				String clientcpf=entrada.nextLine();
				Cliente cli=baseCliente.find(clientcpf);	
				if(cli==null) {
					System.out.println("Cliente não encontrado.\n");
				}
				else {
					System.out.println(cli.toString());
				}
				break;
				
			case "4"://Listar clientes em ordem alfabetica
				List<Cliente> lc =baseCliente.find();
				Principal.listarCliente(lc);
				break;
			
			case "5": //Atualizar cliente pelo cpf
				System.out.println("Digite o cpf do respectivo cliente: ");
				String cpfc = entrada.nextLine();
				if (baseCliente.find(cpfc) != null ) {
					Cliente c2 = Principal.criarCliente();
					if (baseCliente.atualizar(cpfc, c2)) {
						System.out.println("Cliente atualizado com sucesso.\n");
					}
					else {
						System.out.println("Erro: não atualizado.\n");
					}
				}
				else {
					System.out.println("Cpf não encontrado!\n");
				}
				break;
				
			case "6": //Inserir filme
				Filme f = Principal.criarFilme();
				if(baseFilme.find(f.getId())== null && baseFilme.adicionar(f)) {
					System.out.println("Filme inserido com sucesso.\n");
				}
				else {
					System.out.println("Já existe um Filme com esse Id.\n");
				}
				break;
				
			case "7": //Deletar Filme por id
				System.out.println("id = ");
				int id = entrada.nextInt();
				entrada.nextLine();
				if(baseFilme.remover(id)) {
					System.out.println("Filme removido com sucesso.\n");
				}
				else {
					System.out.println("Nao foi possivel remover o Filme!\n");
				}
				break;
				
			case "8": //Buscar filme pelo id	
				System.out.println("id = ");
				int filmid = entrada.nextInt();
				entrada.nextLine();
				Filme film = baseFilme.find(filmid);	
				if(film==null) {
					System.out.println("Filme não encontrado!\n");
				}
				else {
					System.out.println(film.toString());
				}
				break;
				
				
			case "9": //Lista todos os filmes
				List<Filme> lf =baseFilme.find();
				Principal.listarFilme(lf);
				break;
				
			case "10": //Atualizar filme pelo id	
				System.out.println("Digite o id do respectivo filme: ");
				int idf = entrada.nextInt();
				entrada.nextLine();
				if (baseFilme.find(idf) != null ) {
					Filme f2 = Principal.criarFilme();
					if (baseFilme.atualizar(idf, f2)) {
						System.out.println("Filme atualizado com sucesso.\n");
					}
					else {
						System.out.println("Erro: não atualizado.\n");
					}
				}
				else {
					System.out.println("Id não encontrado!\n");
				}
				break;
				
				//TO DO datas validas
			case "11": //alugar filme por id do filme e cpf do cliente
				Locacao l=Principal.criarLocacao();
				if (baseLocacao.find(l.getId_filme())==null && baseCliente.find(l.getCpf_cliente()) != null &&
						baseFilme.find(l.getId_filme())!=null) {
					if (baseLocacao.alugarFilme(l)) {
						System.out.println("Filme alugado com sucesso.\n");
						System.out.println(l.toString());
					}
					else {
						System.out.println("Erro ao tentar alugar o filme.\n");
					}
				}
				else {
					System.out.println("Locação indisponivel.\n");
				}
				break;
				
				
			case "12": 
				 // listar filmes de um cliente alugados
				String cpff;
				System.out.println("Digite o cpf do cliente: ");
				cpff=entrada.nextLine();
				List<Filme> listaFilmes=baseLocacao.find(cpff);
				Principal.listarFilme(listaFilmes);
				
				break;
				
			case "13": 
				 // remover um filme de um cliente alugados
				System.out.println("id do filme:");
				int id_f =Integer.parseInt(entrada.nextLine());
				System.out.println("cpf do cliente");
				String cpf_c=entrada.nextLine();
				if(baseLocacao.removerFilme(id_f, cpf_c)) {
					System.out.println("filme removido com sucesso.\n");
				}else {
					System.out.println("nao foi possivel remover.\n");
				}
				break;
			case "14": 
				 // listar todos os filmes alugados
				List<Filme>listFilmes=baseLocacao.find();
				Principal.listarFilme(listFilmes);
				break;
			case "15":// encerrar
				System.exit(0);
			default :
				System.out.println("opcao invalida!!!\n");
				break;
			}
			System.out.println("Pressione Enter para continuar...\n");
			
			String c = entrada.nextLine();

		} while (true);
	}
}



