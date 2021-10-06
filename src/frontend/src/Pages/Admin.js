import React, {useEffect, useState} from "react";
import {Empty, Spin, Table} from "antd";
import {LoadingOutlined} from "@ant-design/icons";
import {getAllAdmins} from "../Services/admin";
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

    const fetchAdmins = () =>
        getAllAdmins()
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setAdmins(data);
                setFetching(false);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchAdmins();
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
        <Sidebar data={
            <div>
                {renderAdmins()}
            </div>
        }/>
    );
}