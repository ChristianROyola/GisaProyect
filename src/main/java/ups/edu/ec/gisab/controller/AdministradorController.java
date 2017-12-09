package ups.edu.ec.gisab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotBlank;

import ups.edu.ec.gisab.dao.AdministradorDao;
import ups.edu.ec.gisab.modelo.Administrador;
import ups.edu.ec.gisab.utilidades.SessionUtils;

/**
 * Clase controlador que permite el farmato de los datos recibidos por del
 * usuario, para poder enviarlos al DAO.
 * 
 * @author Chriss
 */

/**
 * @author Chriss
 *
 */
@ManagedBean
@SessionScoped
public class AdministradorController {

	private Administrador administrador = null;

	private int id;
	private String pactual;
	private int idEditAdmin;

	@NotBlank(message = "Ingrese sus Credenciales")
	private String contrasenia;
	private String conincidencia;
	private String Loginexiste;
	private String nusuario;
	private int idrecuprerar;
	@Inject
	private AdministradorDao pdao;
	private List<Administrador> ladmins;
	private Administrador myUser;
	private List<Administrador> ListAdminID;

	/**
	 * Expresion regular para validacion de email. validarCorreo();
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Inicializacion de metodos. Con init temporal.
	 */
	@PostConstruct
	public void init() {
		administrador = new Administrador();
		ladmins = listaAdmin();
		ListAdminID = new ArrayList<Administrador>();
	}

	/*
	 * Setea las variable como vacias, ocupado al momento de haber creado el usuario
	 * y dejar los h:inputText del JSF en blanco
	 */
	public void inicializar() {
		administrador.setId(0);
		administrador.setApellido("");
		administrador.setNombre("");
		administrador.setContrasenia("");
	}

	/**
	 * Almacenar Datos de administrador actualizados
	 * 
	 * @param id
	 * @return pages recuperarAdmin
	 */
	public String loadDatosEditar(int id) {
		administrador = pdao.selectAdmin(id);
		pactual = administrador.getContrasenia();
		if (administrador.getPerfil().equals("USUARIO")) {
			return "recuperaPersona";
		} else if (administrador.getPerfil().equals("ADMIN")) {
			return "editAdmin";
		}
		return null;
	}

	/**
	 * Metodo que permite la creacion de un nuevo Administrador 
	 * definiendo el perfil y el estado con el cual se va a crear
	 * el mismo. 
	 */
	public void crearAdmin() {
		if (coincidirContrasenia() == true) {
			if (validarCorreo() == true) {
				administrador.setPerfil("ADMIN");
				administrador.setEstado("A");
				pdao.guardar(administrador);
				inicializar();
				init();
				this.conincidencia = "ADMIN CREADO"
			} else {
				this.conincidencia = "Correo es incorrecto";
			}
		} else {
			this.conincidencia = "Revise contrasenias";
		}
	}

	/**
	 * Verificacion de igualdad de contraseñas al momento del registro de uno nuevo,
	 * como validacion de credenciales.
	 * 
	 * @return true/false
	 */
	public boolean simContraseñas() {
		if (administrador.getContrasenia().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo para verificar la valides del correo ingresado y a ser almacenado.
	 * 
	 * @return True/false
	 */
	public boolean validarCorreo() {
		String email = administrador.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Metodo Listado de los Administradores
	 * 
	 * @return administradores
	 */
	public List<Administrador> listaAdmin() {
		ladmins = pdao.listAdmin();
		return ladmins;
	}

	/**
	 * --------------------------------------------- PARAMETROS PARA CRUD
	 * ------------------------------
	 */

	/**
	 * Metodo Edicion de los administradores del sitio
	 * 
	 * @return Tipo presentacion a mostrar (Pages admin , pages-blanck,)
	 */
	public String modificarAdmin() {
		try {
			if (myUser.getPerfil().equals("ADMIN")) {
				administrador.setContrasenia(pactual);
				pdao.updateAdmin(administrador);
				return "mainAdmin";

			} else if (myUser.getPerfil().equals("ADMIN-SUPER")) {
				administrador.setContrasenia(pactual);
				pdao.updateAdmin(administrador);
				return "pages-blank";

			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public String leerAdmins(int id) {
		administrador = pdao.selectAdmin(id);
		return "crearAdmin";
	}

	/**
	 * Almacena los datos del administrador despues de ser validados y se los
	 * registra en una lista de administradores para su posterior vista.
	 */

	public void cargarDatosAdministrador() {
		myUser = new Administrador();
		HttpSession session = SessionUtils.getSession();
		String nus = (String) session.getAttribute("username");
		try {
			if (pdao.verificaCorreo(nus).size() != 0) {
				List<Administrador> lusuario = new ArrayList<Administrador>();
				lusuario = pdao.verificaCorreo(nus);
				myUser = lusuario.get(0);
			} else {
				FacesContext contex = FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo eliminar admin.
	 * 
	 * @param id
	 * @return pages actualizar
	 */

	public String eliminarAdmins(int id) {
		pdao.deletePersona(id);
		return "actualizar";
	}

	/**
	 * Establecer parametros de sesion para acceso a propiedades JSF con la
	 * implementacion de HTTPSession y SessionUtils, determinando el perfil asi como el 
	 * estado de cada administrador.
	 */

	public void iniciarSesion() {
		if (pdao.login(administrador.getCorreo(), 
				administrador.getContrasenia()).size() != 0) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username",
					pdao.login(administrador.getCorreo(), 
							administrador.getContrasenia()).get(0).getCorreo());
			session.setAttribute("perfil",
					pdao.login(administrador.getCorreo(),
							administrador.getContrasenia()).get(0).getPerfil());
			session.setAttribute("estado",
					pdao.login(administrador.getCorreo(), 
							administrador.getContrasenia()).get(0).getEstado());
			
			FacesContext contex = FacesContext.getCurrentInstance();
			
			if (pdao.login(administrador.getCorreo(), 
					administrador.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN-SUPER")) {
				try {
					contex.getExternalContext().redirect("pages-blank.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (pdao.login(administrador.getCorreo(), 
					administrador.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN")) {
				try {
					contex.getExternalContext().redirect("crearPersona.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.Loginexiste = " ";
		}
		administrador.setCorreo("");
		administrador.setContrasenia("");
		this.Loginexiste = "El usuario o la contrasenia son incorrectos";
	}


	/**
	 * Despues de validar los datos ingresados por login, estos se procede a validar
	 * el inicio de session.
	 */

	public void verificaSesion() {
		HttpSession session = SessionUtils.getSession();
		String nusv = (String) session.getAttribute("ADMIN");
		if (nusv != null) {
			FacesContext contex = FacesContext.getCurrentInstance();
			try {
				if (myUser.getPerfil().equals("ADMIN")) {
					contex.getExternalContext().redirect("mainAdmin.xhtml");
				} else if (myUser.getPerfil().equals("ADMIN-ROOT")) {
					contex.getExternalContext().redirect("mainAdminroot.xhtml");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Consulta de los administradores registrados
	 */
	
	public String consultaContent() {
		ListAdminID = pdao.listAdminID(idrecuprerar);
		for (Administrador p : ListAdminID) {
			nusuario = p.getNombre();
		}
		return null;
	}

	public void loadidUser(int id) {
		idEditAdmin = id;
	}

	public void loadid(int id) {
		idrecuprerar = id;
	}

	public String getNusuario() {
		return nusuario;
	}

	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}
/*
	public int getIdrecuprerar() {
		return idrecuprerar;
	}

	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		loadid(idrecuprerar);
	}
*/
	public List<Administrador> getListPerID() {
		return ListAdminID;
	}

	public void setListPerID(List<Administrador> listPerID) {
		ListAdminID = listPerID;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConincidencia() {
		return conincidencia;
	}

	public void setConincidencia(String conincidencia) {
		this.conincidencia = conincidencia;
	}

	public String getLoginexiste() {
		return Loginexiste;
	}

	public void setLoginexiste(String loginexiste) {
		Loginexiste = loginexiste;
	}

	public Administrador getAdmin() {
		return administrador;
	}

	public void setAdmin(Administrador admin) {
		this.administrador = admin;
	}

	public List<Administrador> getLadmins() {
		return ladmins;
	}

	public void setLadmins(List<Administrador> ladmins) {
		this.ladmins = ladmins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEditAdmin() {
		return idEditAdmin;
	}

	public void setIdEditAdmin(int idEditAdmin) {
		this.idEditAdmin = idEditAdmin;
	}

	public AdministradorDao getPdao() {
		return pdao;
	}

	public void setPdao(AdministradorDao pdao) {
		this.pdao = pdao;
	}

	public Administrador getMyUser() {
		return admin;
	}

	public void setMyUser(Administrador admin) {
		this.myUser = admin;
	}

	public String redirectmainAdmin() {
		return "crearPersona.xhtml";
	}
}
