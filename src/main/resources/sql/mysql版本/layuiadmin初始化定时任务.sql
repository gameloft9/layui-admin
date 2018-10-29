/*
定时任务，每20分钟调用存储过程初始化数据库
*/
CREATE EVENT IF NOT EXISTS `evt_init_db`
ON SCHEDULE EVERY 20 MINUTE
ON COMPLETION PRESERVE
DO call prc_init_db();