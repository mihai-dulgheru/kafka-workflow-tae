# Prerequisites

- Docker Desktop <https://docs.docker.com/desktop/install/windows-install/>
- Eclipse: <https://www.eclipse.org/downloads/>
- OffsetExplorer2: <https://www.kafkatool.com/>
- Postman: <https://www.postman.com/>

## How to run

1. Start Docker Desktop.
2. Run from the `runtime` directory the following command:

   ```bash
   docker-compose up
   ```

3. Run the applications `src/main/java/demo/buyer/BuyerApplication` and `src/main/java/demo/supplier/SupplierApplication`.
4. Invoke from Postman with POST <http://localhost:8081/purchase-orders> having in the header `Content-Type:application/xml` and in the body an XML input for transforming `order2invoice.xsl`.
5. Follow the Kafka cluster with `OffsetExplorer2` in which the cluster with host `localhost` and port `22181` is registered by `Zookeeper`, setting in the Advanced tab as bootstrap servers `localhost:39092` which corresponds to a Kafka worker started in `docker-compose.yml`.

## Stops containers and removes containers, networks, volumes, and images created by up

```bash
docker compose down
```

## Remove all unused containers, networks, images (both dangling and unreferenced), and optionally, volumes

```bash
docker system prune -a --volumes
```
