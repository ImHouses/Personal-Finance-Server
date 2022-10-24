# Running the App
Before running locally, we need to install Docker.

## Make changes
After making changes, the app needs to be rebuilt in order to test the changes locallly.
The following command will rebuild the app.
```bash
docker compose build app
```

## Running
Then, the following command is enough:
```bash
docker compose -f docker-compose.yml -f development.yml --env-file ./compose/dev.env up
```


## TODO

- [x] Create a Docker Compose file to run locally two containers:
  one for the database and another for the MongoDB database.
- [ ] Deploy App on DigitalOcean and connect it to MongoDB Atlas.
- [ ] Configure GitHub Actions to deploy dev/production images when merging to master.