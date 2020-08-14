package by.edabudet.persistence.dao.servises.implementations;

import by.edabudet.bean.Subcategory;
import by.edabudet.persistence.dao.repositories.SubcategoryRepository;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SubcategorySimpleServiceImpl implements SimpleService<Subcategory> {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> findAllJoinCategory() throws SQLException {
        return this.subcategoryRepository.findAllJoinCategory();
    }
    public List<Subcategory> findAllFirstPart() throws SQLException {
        return this.subcategoryRepository.findAllFirstPart();
    }


    public List<Subcategory> findAllSecondPart() throws SQLException {
        return this.subcategoryRepository.findAllSecondPart();
    }

    @Override
    public void save(Subcategory category) throws SQLException {
        this.subcategoryRepository.save(category);
    }

    @Override
    public void update(Subcategory category) throws SQLException {
        this.subcategoryRepository.update(category);
    }

    @Override
    public Subcategory getByName(String name) throws SQLException {
        return this.subcategoryRepository.getByName(name);
    }

    @Override
    public void delete(Long id) throws SQLException {
        this.subcategoryRepository.delete(id);
    }

    @Override
    public List<Subcategory> findAll() throws SQLException {
        return this.subcategoryRepository.findAll();
    }
}

