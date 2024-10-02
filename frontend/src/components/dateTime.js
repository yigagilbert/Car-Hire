import * as React from 'react';
import * as ReactDOM from 'react-dom';

import { DateTimePicker } from '@progress/kendo-react-dateinputs';

class EventLog extends React.Component {
    renderLogs = () => this.props.logs.map((log, index) =>
        (<li key={index}>{log}</li>)
    );
    render() {
        return (
            <div className="example-config">
                <h5>{this.props.title}</h5>
                <ul className="event-log" style={{ maxHeight: '300px' }}>
                    {this.renderLogs()}
                </ul>
            </div>
        );
    }
}

class App extends React.Component {
    logs = [];
    constructor(props) {
        super(props);

        this.state = {
            value: new Date(),
            events: this.logs
        };
    }
    handleChange = (event) => {
        this.logs.unshift("change: " + event.target.value);

        this.setState({
            value: event.target.value,
            events: this.logs.slice()
        });
    }
    render() {
        return (
            <div className="row">
                <div className="col-md-6">
                    <DateTimePicker
                        onChange={this.handleChange}
                        value={this.state.value}
                    />
                </div>
                <div className="col-md-6">
                    <EventLog logs={this.state.events} title={"DateTimePicker Events"} />
                </div>
            </div>
        );
    }
}