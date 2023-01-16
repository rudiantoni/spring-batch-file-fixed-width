# spring-batch-file-fixed-width
- Example project that uses an fixed width file as data source.
- Fixed width files are files whose columns are defined by a set of initial and final indexes, where each range defines a separate column, or field.
- Using a single PostgreSQL database for metadata persisting.
- There's a way to intentionally throw an error, so you can test the application execution management.
- Dockerized project: both application and database runs in separate containers.
- Automation oriented: most settings are done via shell scripts and environment variables.

## Requirements
- Operational system Linux Ubuntu or related distros.
- Docker
    - Instructions: [Install Docker Engine on Ubuntu | Docker Documentation](https://docs.docker.com/engine/install/ubuntu/)
- Docker compose
    - Instructions: [Install the Compose standalone | Docker Documentation](https://docs.docker.com/compose/install/other/)
- Java (JRE/JDK)
    - Recommended JRE version 17.
    - Instructions: [OpenJDK: Download and install](https://openjdk.org/install/)

## Running
- Run the file *deploy.sh* with: `sh deploy.sh`.
    - It uses the following command line argument(s):
        - customerFile: data source file, e.g.: `customerFile=file://files/customers.txt`
- For didatic purposes (like connecting and checking the database, checking logs, etc), the application doesn't clean the created containers automatically when finishes it's execution, so just follow the *Stopping* section instructions in order to fully stop and clean the application containers.

### Other settings
- Between various other settings available in the file *environment.env*, the ones that you may be interested are the following:
    - **JOB_PARAMETER_CUSTOMER_FILE**: data source file name (without trailing and leading slash): Default: *customers.txt*. 
    - **JOB_PARAMETER_CUSTOMER_FILE_DIR**: data source file directory (without trailing slash): Default: *../files*.
        - Note: Currently the data source file (customers.txt) is located at: *./docker/files*. It's mounted as a volume, sharing its contents with the container.

## Stopping
- Run the file *stop.sh* with: `sh stop.sh` to fully stop and remove the application containers.
    - You don't need to stop the application if you want to run it again.

Log image with working example:
- [spring-batch-file-fixed-width](https://i.imgur.com/iBSSCLd.png)
