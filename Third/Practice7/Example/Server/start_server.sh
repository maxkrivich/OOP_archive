java -cp ./compute.jar:./ -Djava.rmi.server.codebase=http://localhost:27015/RMI/ -Djava.rmi.server.hostname=localhost -Djava.security.policy=program.policy engine.ComputeEngine
