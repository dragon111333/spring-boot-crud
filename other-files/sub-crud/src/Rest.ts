import axios from "axios";

export class Rest{
    public static async post(url : string,body : object) : Promise<any>{
        let data = JSON.stringify(body);

        let config = {
            method:"post",
            maxBodyLength: Infinity,
            url: url,
            headers: { 
                'Content-Type': 'application/json'
              },
              data : data
          };
          
        return (await axios.request(config)).data;
    }
}