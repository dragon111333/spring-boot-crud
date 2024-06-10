import express,{ Request, Response }  from "express";
import { JwtPayload } from "./jwt-payload";
import jwt from "jsonwebtoken";
import fs,{ readFile, readFileSync } from "fs";
import { Rest } from './Rest';

const app  = express();
const decodeEndponit : string = "http://localhost:8080/test/jwt-decode";

app.post("/", async (request : Request, response :Response) => {
    try {

        const payload : JwtPayload = {
            aud : "ABC",
            sub : "abc-defg-hijk",
            azp :"test-123-test"
        };
        const options : object = {
            expiresIn: "1h" ,
            algorithm: 'RS256'
        };

        const secret : string  = fs.readFileSync("./rsa.private","utf-8");
        console.log(secret);
        const token : string  = jwt.sign(payload,secret,options);
        
        const resFromEndpoint = await Rest.post(decodeEndponit,{token});
        console.log("RESPONSE --->",resFromEndpoint);
        return response.json({token,resFromEndpoint});
    } catch (e: any) {
        console.error(e);
        return response.status(500).json({status : false , message : "ERROR"});
    }
});


app.listen(3000,()=>{
    console.log("SUB CRUD IS RUNNING.....");
});