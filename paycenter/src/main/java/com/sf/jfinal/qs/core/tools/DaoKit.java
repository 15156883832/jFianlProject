package com.sf.jfinal.qs.core.tools;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import net.jojowo.sqlit.utils.SqlKit;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DaoKit {

    public static <M extends Model> M findFirstBy(Model<M> model, String attr, Object value) {
        TableMapping me = TableMapping.me();
        Table table = me.getTable(model.getClass());
        SqlKit kit = new SqlKit()
                .append("select * from `" + table.getName() + "`")
                .append("where `" + attr + "`=?");

        kit.last("limit 1");
        return model.findFirst(kit.toString(), value);
    }

    public static <M extends Model> M findFirst(Model<M> model, Map<String, Object> conditions) {
        TableMapping me = TableMapping.me();
        Table table = me.getTable(model.getClass());
        SqlKit kit = new SqlKit()
                .append("select * from `" + table.getName() + "`")
                .append("where 1=1");

        Set<String> attrs = conditions.keySet();
        for (String attr : attrs) {
            kit.append("and `" + attr + "`=?");
        }
        kit.last("limit 1");

        return model.findFirst(kit.toString(), conditions.values().toArray());
    }

    public static <M extends Model> List<M> findAll(Model<M> model, boolean includingDeletedAt) {
        String sql = "select * from `" + TableMapping.me().getTable(model.getClass()).getName() + "`";
        return model.find(sql);
    }
    public static <M extends Model> long getcount(Model<M> model,String siteId) {
    	SqlKit kit = new SqlKit()
        .append("select count(*) as count  from `" + TableMapping.me().getTable(model.getClass()).getName() + "`")
        .append("where 1=1 and id != '"+siteId+"' and due_time >= sysdate() and share_code_site_parent_id ='"+siteId+"'");
		
    	return model.getLong(kit.toString());
    }
 
}
