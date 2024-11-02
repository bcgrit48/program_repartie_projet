Prerequisites

    Java 8 or higher
    Network connection (local network or accessible IP for remote connection)

Setup Instructions

    Configure Server:
        Compile and run the server application from the Main class.
        Ensure the server’s IP and port (default: 12345) are known. This IP/port is required for clients to connect.

    Configure Client A:
        In Client A, replace "localhost" with the server’s IP address if Client A is on a different machine.
        Compile and run Client A. Client A should send a number to the server.

    Configure Client B:
        In Client B, replace "localhost" with the server’s IP address if Client B is on a different machine.
        Compile and run Client B. Client B will receive the number from the server and can start guessing.

    Networking:
        Ensure that the server’s port (default 12345) is open on the firewall and accessible on the network.
        If connecting over a local network, ensure all devices are on the same network.
        If connecting remotely, port forwarding may be required on the server’s router.

    Running the Application:
        Start the server first.
        Start Client A and submit a number.
        Start Client B to begin guessing.

    Error Handling:
        If there is an error connecting, ensure the IP and port are correctly configured.
        Verify network connectivity between clients and the server.

Français
Prérequis

    Java 8 ou supérieur
    Connexion réseau (réseau local ou IP accessible pour connexion à distance)

Instructions de Configuration

    Configurer le Serveur :
        Compiler et exécuter l’application serveur à partir de la classe Main.
        Notez l’adresse IP et le port du serveur (par défaut : 12345). Ces informations sont nécessaires pour que les clients puissent se connecter.

    Configurer le Client A :
        Dans le code de Client A, remplacez "localhost" par l’adresse IP du serveur si Client A est sur une autre machine.
        Compiler et exécuter Client A. Client A doit envoyer un nombre au serveur.

    Configurer le Client B :
        Dans le code de Client B, remplacez "localhost" par l’adresse IP du serveur si Client B est sur une autre machine.
        Compiler et exécuter Client B. Client B recevra le nombre depuis le serveur et pourra commencer à deviner.

    Configuration Réseau :
        Assurez-vous que le port du serveur (par défaut 12345) est ouvert dans le pare-feu et accessible sur le réseau.
        Si vous utilisez un réseau local, assurez-vous que tous les appareils sont sur le même réseau.
        En cas de connexion à distance, il peut être nécessaire de configurer le redirection de port sur le routeur du serveur.

    Exécution de l'Application :
        Démarrez d’abord le serveur.
        Lancez Client A et soumettez un nombre.
        Lancez ensuite Client B pour commencer à deviner.

    Gestion des Erreurs :
        En cas d’erreur de connexion, vérifiez que l’adresse IP et le port sont correctement configurés.
        Assurez-vous de la connectivité réseau entre les clients et le serveur.
