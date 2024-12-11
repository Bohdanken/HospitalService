@echo off
rem Licensed to the Apache Software Foundation (ASF) under one
rem or more contributor license agreements.  See the NOTICE file
rem distributed with this work for additional information
rem regarding copyright ownership.  The ASF licenses this file
rem to you under the Apache License, Version 2.0 (the
rem "License"); you may not use this file except in compliance
rem with the License.  You may obtain a copy of the License at
rem
rem   http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing,
rem software distributed under the License is distributed on an
rem "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
rem KIND, either express or implied.  See the License for the
rem specific language governing permissions and limitations
rem under the License.

set ARTEMIS_HOME="D:\Programming programs)\apache-artemis-2.38.0"
set ARTEMIS_INSTANCE="D:\Programming_projects\HospitalApp\AuthService\my-broker"
set ARTEMIS_DATA_DIR="D:\Programming_projects\HospitalApp\AuthService\my-broker\data"
set ARTEMIS_ETC_DIR="D:\Programming_projects\HospitalApp\AuthService\my-broker\etc"
set ARTEMIS_OOME_DUMP="D:\Programming_projects\HospitalApp\AuthService\my-broker\log\oom_dump.hprof"


rem The logging config will need an URI
rem this will be encoded in case you use spaces or special characters
rem on your directory structure
set ARTEMIS_INSTANCE_URI="file:/D:/Programming_projects/HospitalApp/AuthService/my-broker/"
set ARTEMIS_INSTANCE_ETC_URI="file:/D:/Programming_projects/HospitalApp/AuthService/my-broker/etc/"

IF "%LOGGING_ARGS%"=="" (set LOGGING_ARGS=-Dlog4j2.configurationFile=%ARTEMIS_INSTANCE_ETC_URI%log4j2-utility.properties)

IF "%JAVA_ARGS%"=="" (set JAVA_ARGS=-Dlog4j2.disableJmx=true --add-opens java.base/jdk.internal.misc=ALL-UNNAMED )

rem Uncomment to enable remote debugging
rem set DEBUG_ARGS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005