' Créez un objet WScript.Shell pour exécuter des commandes
Set objShell = CreateObject("WScript.Shell")

' Obtenez le répertoire du script VBS
scriptPath = CreateObject("Scripting.FileSystemObject").GetParentFolderName(WScript.ScriptFullName)

' Définissez le chemin complet vers le fichier batch
batchFile = scriptPath & "\Exe.bat"

' Exécutez le fichier batch sans afficher la console
objShell.Run batchFile, 0, False
