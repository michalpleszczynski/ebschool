import groovy.sql.Sql

class GroovyTestDataHandler {
    
    private String url;
    private String username;
    private String password;
    private String driver;
    private String schema;
    private properties;
    private sql;

    private boolean configured;

    private String getConfigValue(name){
        properties.findAll { item -> item.attributes().name?.contains(name) }[0].value()[0]
    }

    def configure(File hibernateCfg){
        assert hibernateCfg.exists()
        String hibernateProperties = hibernateCfg.text
        def records = new XmlParser().parseText(hibernateProperties)
        properties = records.value()[0].value()
        url = getConfigValue('connection.url')
        username = getConfigValue('username')
        password = getConfigValue('password')
        driver = getConfigValue('driver_class')
        schema = url.split('/')[-1]
        sql = Sql.newInstance(url, username, password, driver)
        configured = true;
    }
    
    def cleanDB() {
        assert configured
        sql.execute('drop schema ' + schema + ';')
        sql.execute('create schema ' + schema + ';')
        sql = Sql.newInstance(url, username, password, driver)
    }
    
    def executeSqlScript(File sqlFile) {
        assert configured
        assert sqlFile.exists()
        def statements = sqlFile.text.split(';')
        statements.each {
            statement -> if (!statement.isAllWhitespace()) sql.execute(statement + ';')
        }
    }
}

//GroovyTestDataHandler groovyData = new GroovyTestDataHandler('../test-hibernate.cfg.xml')
//
//groovyData.cleanDB()
//groovyData.executeSqlScript(new File('../sql-scripts/schema.sql'))
//groovyData.executeSqlScript(new File('../datasets/mysql-big-dataset.sql'))