package tests;

import connections.ConnectionConnect;
import connections.ConnectionConnectClass;
import connections.ConnectionObject;
import connections.ContextClass;
import dtos.DTOBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import repositories.IRepository;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

	private static final String url = "jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza";
	private static final String user = "s18389";
	private static final String pass = "oracle12";

	private static final ContextClass _context;

	static{
		ConnectionConnect connectionConnect = new ConnectionConnectClass();
		ConnectionObject connectionObject = new ConnectionObject(url, user, pass);
		_context = new ContextClass(connectionConnect, connectionObject);
	}

	private TRepository _repository;

	protected abstract TRepository getRepositoryFromContextClass();
	protected ContextClass getContext(){return _context;}
	protected TRepository getRepository(){
		if(_repository == null) _repository = getRepositoryFromContextClass();
		Assert.assertNotNull(_repository);
		Assert.assertNotNull(_repository.getContext());
		Assert.assertNotNull(_repository.getConnection());
		return _repository;
	}


	@Before
	public void before() {
		_repository = Create();
		if (_repository != null) {
			_repository.beginTransaction();
		}
	}

	@After
	public void after() {
		if (_repository != null) {
			_repository.rollbackTransaction();
		}
	}

	private void beginTransaction(){
		TRepository repository = getRepository();
		repository.beginTransaction();
	}

	public void rollbackTransaction(){
		TRepository repository = getRepository();
		repository.rollbackTransaction();
	}

	public void commitTransaction(){
		TRepository repository = getRepository();
		repository.commitTransaction();
	}

	protected abstract TRepository Create();
}