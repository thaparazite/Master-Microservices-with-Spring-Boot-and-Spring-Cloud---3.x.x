# Kubernetes and Microservices

## Images

You can reuse these images instead of creating and pushing new container images

These are pre-built Docker images for the services involved in the system. 
Instead of creating new images, you can reuse these existing container images:
- Currency Exchange Service 
	- v11 - in28min/mmv3-currency-exchange-service:0.0.11-SNAPSHOT
  - v12 - in28min/mmv3-currency-exchange-service:0.0.12-SNAPSHOT
- Currency Conversion Service
	- in28min/mmv3-currency-conversion-service:0.0.11-SNAPSHOT
    - Uses CURRENCY_EXCHANGE_SERVICE_HOST
  - in28min/mmv3-currency-conversion-service:0.0.12-SNAPSHOT
    - Uses CURRENCY_EXCHANGE_URI

## URLS

#### Currency Exchange Service
Used to fetch currency exchange rates.
- http://localhost:8000/currency-exchange/from/USD/to/INR

#### Currency Conversion Service
Used to convert a specific quantity of currency.
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10


#### Commands
```
## 1. Docker Commands:
    - Runs a Docker container for the hello-world-rest-api service on port 8080:
    docker run -p 8080:8080 in28min/hello-world-rest-api:0.0.1.RELEASE

## 2. Kubernetes Commands:
    - Creates a deployment for hello-world-rest-api using the specified image:
    kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE

    - Exposes the deployment as a service, making it accessible on port 8080:
    kubectl expose deployment hello-world-rest-api --type=LoadBalancer --port=8080

    - Scales the deployment to 3 replicas to ensure availability:
    kubectl scale deployment hello-world-rest-api --replicas=3

    - Deletes a specific pod by its name (useful for troubleshooting):
    kubectl delete pod hello-world-rest-api-58ff5dd898-62l9d

    - Autoscalers will adjust the number of replicas based on CPU usage. The deployment scales up to a maximum of 10 pods when CPU usage exceeds 70%:
    kubectl autoscale deployment hello-world-rest-api --max=10 --cpu-percent=70

    - Edits the deployment to ensure that the pod has a minimum ready time of 15 seconds before it can be considered available:
    kubectl edit deployment hello-world-rest-api #minReadySeconds: 15

    - Updates the deployment to use a new version of the image (version 0.0.2.RELEASE):
    kubectl set image deployment hello-world-rest-api hello-world-rest-api=in28min/hello-world-rest-api:0.0.2.RELEASE

## 3. Google Cloud and Kubernetes Operations: 
    - Configures kubectl to use the credentials of the specified Kubernetes cluster in Google Cloud:
    gcloud container clusters get-credentials in28minutes-cluster --zone us-central1-a --project solid-course-258105

    - Creates the deployment in the Kubernetes cluster:
    kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE

    - Exposes the service, making it available externally through a load balancer on port 8080:
    kubectl expose deployment hello-world-rest-api --type=LoadBalancer --port=8080

    - Sets a dummy image, usually for testing purposes:
    kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST

    -Fetches and sorts Kubernetes events by creation timestamp, useful for troubleshooting:
    kubectl get events --sort-by=.metadata.creationTimestamp

    - Updates the deployment to use a new image version (0.0.2.RELEASE):
    kubectl set image deployment hello-world-rest-api hello-world-rest-api=in28min/hello-world-rest-api:0.0.2.RELEASE

    - Fetches and sorts Kubernetes events by creation timestamp, useful for troubleshooting:
    kubectl get events --sort-by=.metadata.creationTimestamp

    - Fetches the status of core Kubernetes components such as scheduler and controller manager:
    kubectl get componentstatuses
    
    - Lists all pods across all namespaces in the cluster
    kubectl get pods --all-namespaces

    - Lists recent Kubernetes events:
    kubectl get events
    
    - Lists all pods in the current namespace:
    kubectl get pods
    
    - Lists all replica sets in the current namespace:
    kubectl get replicaset
    
    - Lists all deployments in the current namespace:
    kubectl get deployment
    
    - Lists all services in the current namespace
    kubectl get service

 ## 4.Pod and Deployment Inspection:
    - Lists all pods with additional details such as IP addresses and node information:
    kubectl get pods -o wide

    - Describes the schema of the 'pods' resource, useful for understanding available fields:
    kubectl explain pods

    - Lists all pods with additional details such as IP addresses and node information:
    kubectl get pods -o wide

    - Shows detailed information about a specific pod, including events, status, and container info:
    kubectl describe pod hello-world-rest-api-58ff5dd898-9trh2

    - Lists the replica sets, which control the number of pods for a deployment:
    kubectl get replicasets
    kubectl get replicaset

    kubectl scale deployment hello-world-rest-api --replicas=3
    kubectl get pods
    kubectl get replicaset
    kubectl get events

    - Shows the events sorted by creation time, helpful in debugging issues with deployments and pods:
    kubectl get events --sort.by=.metadata.creationTimestamp

    kubectl get rs
    kubectl get rs -o wide

  ## 5.Advanced Kubernetes Operations:
    - Tests deployment updates by using a dummy image:
    kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST
    kubectl get rs -o wide
    kubectl get pods
    kubectl describe pod hello-world-rest-api-85995ddd5c-msjsm

    - Shows the events sorted by creation time, helpful in debugging issues with deployments and pods:
    kubectl get events --sort-by=.metadata.creationTimestamp

    - Updates deployment to use a new image version:
    kubectl set image deployment hello-world-rest-api hello-world-rest-api=in28min/hello-world-rest-api:0.0.2.RELEASE
    kubectl get events --sort-by=.metadata.creationTimestamp
    kubectl get pods -o wide
    
    - Deletes specific pods to simulate failure or trigger recreation:
    kubectl delete pod hello-world-rest-api-67c79fd44f-n6c7l
    
    kubectl get pods -o wide
    
    - Deletes specific pods to simulate failure or trigger recreation
    kubectl delete pod hello-world-rest-api-67c79fd44f-8bhdt

    - Authenticates kubectl with another cluster in a different zone:
    gcloud container clusters get-credentials in28minutes-cluster --zone us-central1-c --project solid-course-258105
    
    - Authenticates Docker CLI with Docker Hub
    docker login
    
    - Pushes updated images to Docker Hub
    docker push in28min/mmv3-currency-exchange-service:0.0.11-SNAPSHOT
    docker push in28min/mmv3-currency-conversion-service:0.0.11-SNAPSHOT
    
    - Deploys the currency-exchange services
    kubectl create deployment currency-exchange --image=in28min/mmv3-currency-exchange-service:0.0.11-SNAPSHOT

    - Expose the service and set the port and type
    kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000

    - Lists various Kubernetes resources:
    kubectl get svc
    kubectl get services
    kubectl get pods
    kubectl get po
    kubectl get replicaset
    kubectl get rs
    kubectl get all

    - Deploys the currency-conversion service 
    kubectl create deployment currency-conversion --image=in28min/mmv3-currency-conversion-service:0.0.11-SNAPSHOT
    
    - Expose the service and set the port and type
    kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100

    - Watches real-time updates of service objects:
    kubectl get svc --watch

    kubectl get deployments

    - Exports the deployment and service definitions to YAML:
    kubectl get deployment currency-exchange -o yaml >> deployment.yaml 
    kubectl get service currency-exchange -o yaml >> service.yaml 

    - Shows diff between live and file definition:
    kubectl diff -f deployment.yaml
    
    - Applies the YAML configuration:
    kubectl apply -f deployment.yaml

    - Deletes all components associated with specific app labels:
    kubectl delete all -l app=currency-exchange
    kubectl delete all -l app=currency-conversion

    - Shows the rollout history for a specific deployment (currency-conversion):
    kubectl rollout history deployment currency-conversion
    kubectl rollout history deployment currency-exchange

    - Rolls back the currency-exchange deployment to a specific revision:
    kubectl rollout undo deployment currency-exchange --to-revision=1

 ## 6.Log Management and Troubleshooting:
    - Retrieves logs from a specific pod.
    kubectl logs currency-exchange-9fc6f979b-2gmn8

    - Follows the logs of the specified pod in real time.
    kubectl logs -f currency-exchange-9fc6f979b-2gmn8 

  ## 7.Horizontal Pod Autoscaling:
    - Autoscale the currency-exchange deployment based on CPU utilization. It scales between 1 and 3 replicas when CPU utilization exceeds 5%:
    kubectl autoscale deployment currency-exchange --min=1 --max=3 --cpu-percent=5 

    - Displays the current horizontal pod autoscalers (HPA) for a deployment:
    kubectl get hpa

    - Displays CPU and memory usage of all pods:
    kubectl top pod
    
    - Displays CPU and memory usage of all nodes:
    kubectl top nodes
    
    - Displays the current horizontal pod autoscalers (HPA) for a deployment:
    kubectl get hpa
    
    - Deletes a Horizontal Pod Autoscaler:
    kubectl delete hpa currency-exchange

 ## 8.ConfigMap Operations:
    - Creates a ConfigMap for storing environment variables, such as the URL for the currency exchange service:
    kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange

    - Retrieves the list of all ConfigMaps:
    kubectl get configmap

    - Exports a ConfigMap to a YAML file:
    kubectl get configmap currency-conversion -o yaml >> configmap.yaml

    - Continuously watches the response of the currency conversion service (shell command):
    watch -n 0.1 curl http://34.66.241.150:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
 
 ## 10.Image Push to Docker Hub:
    - Pushes the updated Docker image for the currency conversion service to Docker Hub:
    docker push in28min/mmv3-currency-conversion-service:0.0.12-SNAPSHOT

    - Pushes the updated Docker image for the currency exchange service to Docker Hub:
    docker push in28min/mmv3-currency-exchange-service:0.0.12-SNAPSHOT
```