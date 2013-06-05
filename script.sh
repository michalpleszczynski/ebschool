#!/bin/bash

deploy() {
  start_dir=`pwd`;
  cd /home/michau/Desktop/Michau/programy/Java/E-Buisness/ebuisness-school/ls-system;
  mvn clean install;
  rm $JBOSS_HOME/standalone/deployments/ls-system.ear*
  cp ls-system-ear/target/ls-system.ear $JBOSS_HOME/standalone/deployments/
  cd $start_dir;
}

recreateDB() {
  echo "Cleaning up the db...";
  mysql -u testit_user --password=testit ebuisness_database < ls-system/ls-system-ejb/src/main/resources/sql/cleanup.sql;
  echo "Creating db schema...";
  mysql -u testit_user --password=testit ebuisness_database < ls-system/ls-system-ejb/src/main/resources/sql/schema.sql;
  echo "Populating db...";
  mysql -u testit_user --password=testit ebuisness_database < ls-system/ls-system-ejb/src/main/resources/sql/mysql-big-dataset.sql;
  echo "Done.";
}

$@
