package gov.usgs.wim.wdnr.dao;

import gov.usgs.wim.wdnr.domain.SanitaryData;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;

@Component
public class SanitaryDataDao extends SqlSessionDaoSupport {

    @Autowired
    public SanitaryDataDao(SqlSessionFactory sqlSessionFactory) {
        setSqlSessionFactory(sqlSessionFactory);
    }

    public void create(SanitaryData sd) {
        getSqlSession().insert("create", sd);
    }

    public Integer getUserid(String username) {
        return getSqlSession().selectOne("username", username);
    }

    public Integer checkUniqueKey(Map<String, Object> params) {
        return getSqlSession().selectOne("checkUniqueKey", params);
    }

    public Integer checkBeachSeq(Map<String, Object> params) {
        return getSqlSession().selectOne("checkBeachSeq", params);
    }

    public Integer checkMonitorSite(Map<String, Object> params) {
        return getSqlSession().selectOne("checkMonitorSite", params);
    }
}
