import React, {useEffect, useState} from "react";
import {Button, Empty, Spin, Table} from "antd";
import axios from 'axios';
import {LoadingOutlined} from "@ant-design/icons";
import {Sidebar} from "../Components/Sidebar"

const columns = [
    {
        title: 'Id',
        dataIndex: 'adminId',
        key: 'adminId',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Password',
        dataIndex: 'password',
        key: 'password',
    },
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
export default function Admin() {
    const [admins, setAdmins] = useState([]);
    const [fetching, setFetching] = useState(true);

    const getAdmins = () =>
        axios.get("api/admins")
            .then(res => {
                console.log(res);
                setAdmins(res.data);
                setFetching(false);
            })

    useEffect(() => {
        console.log("component is mounted");
        getAdmins().then(r => console.log(r));
    }, []);

    const renderAdmins = () => {
        if(fetching){
            return <Spin indicator={antIcon} />
        }
        if(admins.length <= 0){
            return <Empty />;
        }
        return <Table
            dataSource={admins}
            columns={columns}
            bordered
            title={() => 'Admins'}
            pagination={{ pageSize: 50 }}
            scroll={{ y: 240 }}
            rowKey = {(admin) => admin.id}
        />;

    }
    return (
        <Sidebar name="Admin" data={
            <div>
                <Button onClick={() => {window.location.pathname = "/AdminCreate"}}>Create admin</Button>
                {renderAdmins()}
            </div>
        }/>
    );
}