using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SKYPE4COMLib;
using System.Net;
using System.IO;

namespace skype
{
    public partial class Form1 : Form
    {
        Skype skype = new Skype();
        public Form1()
        {
            InitializeComponent();

            skype.MessageStatus += new _ISkypeEvents_MessageStatusEventHandler(skype_MessageStatus);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                skype.Attach();
                MessageBox.Show("Połączono ze Skype!");
            }
            catch { MessageBox.Show("Błąd połączenia!"); }
        }

        private void skype_MessageStatus(ChatMessage msg, TChatMessageStatus status)
        {
            var webAddr = "http://81.2.243.96:8080/runtime/process-instances";
            var httpWebRequest = (HttpWebRequest)WebRequest.Create(webAddr);
            httpWebRequest.ContentType = "application/json; charset=utf-8";
            System.Net.NetworkCredential userDefined = new System.Net.NetworkCredential("kermit", "kermit");
            httpWebRequest.Credentials = userDefined;
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                string json = $"{{ \"processDefinitionId\":\"process:32:46610\",\"variables\": [{{\"name\":\"{msg.Sender.FullName}\",\"value\":\"{msg.Body}\",}}]}}";

                streamWriter.Write(json);
                streamWriter.Flush();
            }

            //var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            //using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            //{
            //    var result = streamReader.ReadToEnd();
            //}
        }

        public class asdf
        {
            public string processDefinitionId { get; set; }
            public string[] variables { get; set; }
        }
    }
}
