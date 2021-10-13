import React, {useState} from "react";
import {Button, Form, Input, Space} from "antd";
import axios from 'axios';
import {MailOutlined, SafetyCertificateOutlined, UserOutlined} from "@ant-design/icons";
import {Sidebar} from "../Components/Sidebar"

export default function PostAdmin() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const ChangeHandler = (e) => {
        switch (e.target.name) {
            case "Name" :
                setName(e.target.value);
                break;
            case "Email" :
                setEmail(e.target.value);
                break;
            case "Password" :
                setPassword(e.target.value);
                break;
            default:
        }
    }
    const headers = {
        'Content-Type': 'application/json'
    }

    const properties = {
        name: name,
        email: email,
        password: password,
    }
    function SubmitHandler(e) {
        e.preventDefault()
        axios.post("./api/admins", JSON.stringify(properties) , {
            headers: headers
        })
            .then(res => {
                console.log(res.data)
            })
    }

    return (
        <Sidebar name="Admin Create" data={
            <div>
                <Form>
                    <Space direction="vertical">
                        <Input name="Name" type="text" value={name} onChange={ChangeHandler} placeholder="name" prefix={<UserOutlined />}/>
                        <Input name="Email" type="text" value={email} onChange={ChangeHandler} placeholder="email" prefix={<MailOutlined />}/>
                        <Input.Password name="Password" type="text" value={password} onChange={ChangeHandler} placeholder="password" prefix={<SafetyCertificateOutlined />}/>
                        <Button type="primary" htmlType="submit" onClick={SubmitHandler}>Submit</Button>
                    </Space>
                </Form>
            </div>
        }/>
    );
}