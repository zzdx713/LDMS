package cn.cug.laboratory.service.impl;

import cn.cug.laboratory.mapper.extend.ProjectExtendMapper;
import cn.cug.laboratory.model.extend.ProjectExtend;
import cn.cug.laboratory.model.persistent.PageModel;
import cn.cug.laboratory.model.persistent.Project;
import cn.cug.laboratory.service.ProjectService;
import cn.cug.laboratory.utils.DBUtils;
import cn.cug.laboratory.utils.RetuValueClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PP on 2016/5/22.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectExtendMapper projectMapper;

    @Autowired
    ProjectExtendMapper projectExtendMapper;

    /**
     * @param id
     * @return 根据主键获取实验
     */
    public Project selectByPrimaryKey(String id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    /**
     * @param teaId
     * @param pageNo
     * @param pageNum
     * @return
     */
//    //pageNo表示第几页，从1开始，pageNum表示每页的记录数目
//    public RetuValueClass<Project> selectByTeaId(String teaId, int pageNo, int pageNum) {
//        int startSite = (pageNo - 1) * pageNum;
//        List<Project> projectList = projectMapper.selectByTeaId(teaId, startSite, pageNum);
//        int projectCount = projectMapper.selectCountByTeaId(teaId);
//        return new RetuValueClass<Project>(projectList, projectCount);
//    }


//    public RetuValueClass<Project> selectByTeaId(String teaId, int pageNo, int pageNum) {
//        int startSite = (pageNo - 1) * pageNum;
//        List<Project> projectList = projectMapper.selectByTeaId(teaId, startSite, pageNum);
//        int projectCount = projectMapper.selectCountByTeaId(teaId);
//        return new RetuValueClass<Project>(projectList, projectCount);
//    }

    /**
     * @param name
     * @param pageNo
     * @param pageNum
     * @return
     */
    public RetuValueClass<Project> selectByName(String name, int pageNo, int pageNum) {
        int startSite = (pageNo - 1) * pageNum;
        List<Project> projectList = projectExtendMapper.selectByName(name, startSite, pageNum);
        int projectCount = projectExtendMapper.selectCountByTeaId(name);
        return new RetuValueClass<Project>(projectList, projectCount);
    }



    /**
     * @author: shixing
     * @function:查询记录条数
     * @since : 1.0.0
     */
    @Override
    public Integer selectByMultipleInfoCounts(ProjectExtend projectExtend) {
        return projectExtendMapper.selectByMultipleInfoCounts(projectExtend);
    }


    @Override
    public List<ProjectExtend> selectByMultipleInfo(Integer currentPage, Integer offset, ProjectExtend projectExtend) {
        return projectExtendMapper.selectByMultipleInfo(currentPage, offset, projectExtend);
    }




    @Override
    public ProjectExtend selectMultipleInfoById(String id) {
        return projectExtendMapper.selectMultipleInfoById(id);
    }
    @Override
    public List<ProjectExtend> selectProByMultipleInfo(Integer currentPage, Integer offset, ProjectExtend projectExtend) {
        return null;
    }

    @Override
    public Integer selectProByMultipleInfoCounts(ProjectExtend projectExtend) {
        return null;
    }



    /**
     * @author: shixing
     * @function:分页查询项目信息
     * @since : 1.0.0
     */
    @Override
    public PageModel<ProjectExtend> getProjectInfoByPage(Integer currentPage, Integer offset, ProjectExtend projectExtend) {
        //获取总的记录数
        Integer totalRecords = projectExtendMapper.selectByMultipleInfoCounts(projectExtend);
        //创建PageModel对象
        PageModel<ProjectExtend> pm = new PageModel<>(currentPage, offset, totalRecords);
        //获取当前页面数据
        List<ProjectExtend> data = projectExtendMapper.selectByMultipleInfo(pm.getStartPosition(), offset, projectExtend);
        //设置数据
        pm.setData(data);
        //返回页面
        return pm;
    }

    @Override
    public void updateProjectStateById(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public PageModel<ProjectExtend> getStuProjectInfoByPage(Integer currentPage, Integer offset, ProjectExtend projectExtend) {
        return null;
    }


    /**
     * @author:HXY
     * @param currentPage
     * @param offset
     * @param project
     * @return
     */
    @Override
    public PageModel<Project> getProjectByMultipleinfo(Integer currentPage, Integer offset, Project project) {
        //获取总的记录数
        Integer totalRecords = projectExtendMapper.getCounts(project);
        //创建PageModel对象
        PageModel<Project> pm = new PageModel<>(currentPage, offset, totalRecords);
        //获取当前页面数据
        List<Project> data = projectExtendMapper.getinfo(pm.getStartPosition(), offset, project);
        //设置数据
        pm.setData(data);
        //返回页面
        return pm;
    }

    @Override
    public String getNewId() {
        return DBUtils.StringAddOne(projectExtendMapper.getLastId());
    }

    @Override
    public int insert(Project project) {
        return projectMapper.insertSelective(project);
    }

    @Override
    public Project getById(String id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void setfilenameBYId(Project project) {
        projectExtendMapper.setfilenameById(project);
    }


}
