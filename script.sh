#!/bin/bash

function deploy() {
  start_dir=`pwd`;
  cd /home/michau/Desktop/Michau/programy/Java/E-Buisness/ebuisness-school/ls-system;
  cd ejb && mvn clean install && cd ..;
  cd rest && mvn clean install && cd ..;
  cd web && mvn clean install && cd ..;
  cd mobile && mvn clean install && cd ..;
  cd ear && mvn clean install && cd ..;
  rm $JBOSS_HOME/standalone/deployments/ls-system.ear*
  cp ear/target/ls-system.ear $JBOSS_HOME/standalone/deployments/
  cd $start_dir;
}

function cleanTestDB() {
  echo "Cleaning up the db...";
  mysql -u testit_user --password=testit ebuisness_test_database -e "drop schema ebuisness_test_database; create schema ebuisness_test_database;";
  echo "Creating db schema...";
  mysql -u testit_user --password=testit ebuisness_test_database < ls-system/ejb/src/main/resources/sql/schema.sql;
  mysql -u testit_user --password=testit ebuisness_test_database < ls-system/ejb/src/main/resources/sql/roles.sql;
  echo "Done.";
}

function recreateDB() {
  echo "Cleaning up the db...";
  mysql -u testit_user --password=testit ebuisness_database -e "drop schema ebuisness_database; create schema ebuisness_database";
  echo "Creating db schema...";
  mysql -u testit_user --password=testit ebuisness_database < ls-system/ejb/src/main/resources/sql/schema.sql;
  mysql -u testit_user --password=testit ebuisness_database < ls-system/ejb/src/main/resources/sql/roles.sql;
  echo "Populating db...";
  mysql -u testit_user --password=testit ebuisness_database < ls-system/ejb/src/main/resources/sql/mysql-big-dataset.sql;
  echo "Done.";
}

function prepareJboss() {
  start_dir=`pwd`;
  cd /home/michau/Desktop/Michau/programy/Java/E-Buisness/ebuisness-school/ls-system/build;
  ant -f prepare-jboss.xml
  cd $start_dir;
}

function start() {
  start_dir=`pwd`;
  cd $JBOSS_HOME/bin;
  sh standalone.sh
  cd $start_dir;
}

$@
