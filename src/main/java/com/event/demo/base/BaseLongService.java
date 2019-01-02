package com.event.demo.base;

import com.yisi.stiku.common.bean.BaseEntity;
import com.yisi.stiku.common.mapper.MyMapper;
import com.yisi.stiku.mathsgo.common.Page;
import com.yisi.stiku.newmathsgo.common.constant.OrderBy;
import com.yisi.stiku.newmathsgo.common.util.OrderByParser;
import com.yisi.stiku.newmathsgo.common.vo.BaseQueryVo;
import com.yisi.stiku.web.util.LoginSesionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author wu
 * @date 2018/08/30
 * @Description:
 */
public abstract class BaseLongService<T extends BaseLongEntity> {
    protected static Logger logger = LoggerFactory.getLogger(BaseLongService.class);
    @Autowired
    public MyMapper<T> mapper;

    /**
     * 若查询不到类，则需要override这个方法
     */
    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 保存对象
     *
     * @param t
     * @return
     */
    public Long save(T t) {
        if (t.getId() == null || t.getId().equals("")) {
            this.insert(t);
            return t.getId();
        } else {
            this.update(t);
            return t.getId();
        }
    }

    /**
     * 插入对象
     *
     * @param t
     * @return
     */
    public void insert(T t) {
        try {
            t.setCreatedBy(LoginSesionUtil.getUserName());
            t.setUpdatedBy(LoginSesionUtil.getUserName());
        } catch (Exception e) {
            logger.debug("用户尚未登录");
        }
        t.setCreatedDt(new Date());
        t.setUpdatedDt(new Date());
        t.setDr(0);
        mapper.insertSelective(t);
    }

    /**
     * 更新对象
     *
     * @param t
     * @return
     */
    public void update(T t) {
        try {
            t.setUpdatedBy(LoginSesionUtil.getUserName());
        } catch (Exception e) {
            logger.debug("用户尚未登录");
        }
        t.setUpdatedDt(new Date());
        mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 查找全部
     *
     * @return
     */
    public List<T> findAll(T t) {
        t.setDr(0);
        return mapper.select(t);
    }

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    public T selectById(Long id) {
        T t = mapper.selectByPrimaryKey(id);
        return t;
    }

    public List<T> selectByIds(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return new ArrayList<>();
        }
        Example example = new Example(getEntityClass());
        example.createCriteria().andIn("id", ids);
        return mapper.selectByExample(example);
    }


    /**
     * 通过实体查找
     */
    public List<T> selectByEntity(T entity) {
        entity.setDr(0);
        return mapper.select(entity);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(Long id) {
        T entity = this.selectById(id);
        entity.setDr(1);
        try {
            entity.setUpdatedBy(LoginSesionUtil.getUserName());
        } catch (Exception e) {
            logger.debug("用户尚未登录");
        }
        entity.setUpdatedDt(new Date());
        this.update(entity);
    }

    /**
     * 通过实体查找
     */
    public T selectOne(T entity) {
        entity.setDr(0);
        return mapper.selectOne(entity);
    }

    /**
     * 通过实体查找
     *
     * @param entity
     * @return
     */
    public List<T> selectByEntity(T entity, String orderByProperty) {
        return selectByEntity(entity, orderByProperty, Sort.Direction.ASC);
    }

    /**
     * 通过实体查找
     *
     * @param entity
     * @return
     */
    public List<T> selectByEntity(T entity, String orderByProperty, Sort.Direction order) {
        entity.setDr(0);
        Example example = toExample(entity);
        Example.OrderBy orderBy = example.orderBy(orderByProperty);
        if (order == Sort.Direction.ASC) {
            orderBy.asc();
        } else if (order == Sort.Direction.DESC) {
            orderBy.desc();
        }
        return mapper.selectByExample(example);
    }

    public Example toExample(T entity) {
        Class tempClass = getEntityClass();
        Example example = new Example(tempClass);
        if (entity == null) {
            return example;
        }
        Example.Criteria criteria = example.createCriteria();
        List<Field> fields = new ArrayList<>();

        while (tempClass != null && !tempClass.getName().equals(BaseEntity.class.getName())) {
            // 到BaseEntity为止
            List<Field> list = Arrays.asList(tempClass.getDeclaredFields());
            for (Field f : list) {
                //获取字段中包含fieldMeta的注解 有transient不做查询
                Transient meta = f.getAnnotation(Transient.class);
                if (meta == null) {
                    fields.add(f);
                }
            }
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(entity);
            } catch (IllegalAccessException e) {
                logger.debug("用户尚未登录");
            }
            if (value == null) {
                continue;
            }
            try {
                criteria.andEqualTo(field.getName(), value);
            } catch (Exception e) {
                logger.debug("用户尚未登录");
            }
        }
        return example;
    }

    /**
     * 分页查询
     *
     * @param entity
     * @return
     */
    public Page<T> findByPageByEntity(T entity, Integer page, Integer rows) {
        Page<T> basePageHelper = new Page<>();
        entity.setDr(0);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Example example = toExample(entity);
        example.orderBy("createdDt").desc();
        Integer count = mapper.selectCountByExample(example);
        List<T> list = mapper.selectByExampleAndRowBounds(example, rowBounds);
        basePageHelper.setCurrentPage(page);
        basePageHelper.setSize(rows);
        basePageHelper.setList(list);
        basePageHelper.setTotalPage((int) Math.ceil((double) count / rows));
        basePageHelper.setTotalElements(count);
        return basePageHelper;
    }

    public Page<T> findByPageByExample(Example example, Integer page, Integer rows) {
        Page<T> basePageHelper = new Page<>();
        Integer count = mapper.selectCountByExample(example);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<T> list = mapper.selectByExampleAndRowBounds(example, rowBounds);
        basePageHelper.setCurrentPage(page);
        basePageHelper.setSize(rows);
        basePageHelper.setList(list);
        basePageHelper.setTotalPage((int) Math.ceil((double) count / rows));
        basePageHelper.setTotalElements(count);
        return basePageHelper;
    }

    public Page<T> findByPageByExampleExtend(Example example, Integer page, Integer rows) {
        Page<T> basePageHelper = new Page<>();
        Integer count = mapper.selectCountByExample(example);
        page = page == null ? 1 : page;
        if (rows == null) {
            rows = count;
        }
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<T> list = mapper.selectByExampleAndRowBounds(example, rowBounds);
        basePageHelper.setCurrentPage(page);
        basePageHelper.setSize(rows);
        basePageHelper.setList(list);
        basePageHelper.setTotalPage((int) Math.ceil((double) count / rows));
        basePageHelper.setTotalElements(count);
        return basePageHelper;
    }

    /**
     * 插入或更新对象
     *
     * @param t
     * @return
     */
    public Long saveOrUpdate(T t) {
        if (t.getId() == null || t.getId().equals("")) {
            this.insert(t);
            return t.getId();
        } else {
            T tt = this.selectById(t.getId());
            if (tt == null) {
                this.insert(t);
                return t.getId();
            } else {
                this.update(t);
                return t.getId();
            }
        }
    }

    public T saveOrUpdateEntity(T t) {
        Long id = saveOrUpdate(t);
        return selectById(id);
    }

    public Page query(BaseQueryVo<T> baseQueryVo) {
        Example example = new Example(getEntityClass());
        Integer pageNo = 1;
        Integer size = null;
        if (baseQueryVo != null) {
            example = toExample(baseQueryVo.getT());
            if (!StringUtils.isBlank(baseQueryVo.getOrderBy())) {
                List<OrderByParser.OrderByCondition> orderByConditions = new OrderByParser().parse(baseQueryVo.getOrderBy());
                for (OrderByParser.OrderByCondition orderByCondition : orderByConditions) {
                    Example.OrderBy orderBy = example.orderBy(orderByCondition.getField());
                    if (orderByCondition.getOrderBy().equals(OrderBy.ASC)) {
                        orderBy.asc();
                    } else if (orderByCondition.getOrderBy().equals(OrderBy.DESC)) {
                        orderBy.desc();
                    }
                }
            }
            pageNo = baseQueryVo.getPage();
            size = baseQueryVo.getPageSize();
        }
        return findByPageByExampleExtend(example, pageNo, size);
    }
}
