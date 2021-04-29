package repositiors;

import connections.ContextClass;
import dtos.DTOBase;
import repositories.IRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class RepositoryBase<TEntity extends DTOBase> implements IRepository<TEntity> {

    private final ContextClass context;

    protected RepositoryBase(ContextClass context) {
        this.context = context;
    }

    public final ContextClass getContext(){ return context; }

    public final Connection getConnection(){ return getContext().getConnection(); }

    public void addOrUpdate(TEntity dto){
        if (exists(dto)) update(dto);
        else add(dto);
    }

    @Override
    public void delete(TEntity dto) {

    }

    @Override
    public TEntity findById(int id) {
        return null;
    }

    @Override
    public void beginTransaction() {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("BEGIN");
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("COMMIT");
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("ROLLBACK");
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return 0;
    }

    public void add(TEntity dto) {
    }

    public void update(TEntity dto) {
    }

    public boolean exists(TEntity dto) {
        boolean ifexists = false;
        if(dto.hasExistingId()){
            TEntity savedEntity = findById(dto.getId());
            ifexists = savedEntity != null;
        }
        return ifexists;
    }
}
