# Running the App
## Storage
For development we can use a local MongoDB instance. So first of all we need to run the following command:
```bash
brew services start mongodb-community@6.0
```
This will start a macOS service. To stop it you have to use:
```bash
brew services stop mongodb-community@6.0
```

## App
The following command is the only thing we need:
```bash
./gradlew :run
```